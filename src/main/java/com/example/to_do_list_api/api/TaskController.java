package com.example.to_do_list_api.api;

import com.example.to_do_list_api.api.dto.TaskRequestDto;
import com.example.to_do_list_api.api.dto.TaskResponseDto;
import com.example.to_do_list_api.api.mappers.TaskMapper;
import com.example.to_do_list_api.persistence.Task;
import com.example.to_do_list_api.persistence.User;
import com.example.to_do_list_api.service.TaskService;
import com.example.to_do_list_api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final TaskMapper taskMapper;

    @GetMapping
    public Page<TaskResponseDto> getAllTasks(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean descending
    ) {
        Sort sort = descending ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        User user = userService.getUserByEmail(authentication.getName());
        return service.getAllTasksByUser(user, pageable).map(taskMapper::toDto);
    }

    @GetMapping("/{taskId}")
    public TaskResponseDto getTaskById(@PathVariable int taskId, Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUserByEmail(userName);
        return taskMapper.toDto(service.getTaskByIdForUser(taskId, user));
    }

    @PatchMapping("/{taskId}")
    public Task updateTaskById(@PathVariable int taskId, @RequestBody Map<String, Object> patch) {
        if (patch.containsKey("id")) {
            throw new RuntimeException("Id is not allowed in the request");
        }
        Task taskToUpdate = service.getTaskById(taskId);
        Task task = applyPatching(taskToUpdate, patch);
        service.addTask(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable int id, Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUserByEmail(userName);
        service.deleteTaskById(id, user);
    }

    private Task applyPatching(Task taskToUpdate, Map<String, Object> patch) {
        ObjectNode patchNode = objectMapper.convertValue(patch, ObjectNode.class);
        ObjectNode taskNode = objectMapper.convertValue(taskToUpdate, ObjectNode.class);
        taskNode.setAll(patchNode);
        return objectMapper.convertValue(taskNode, Task.class);

    }

    @PostMapping
    public TaskResponseDto addTask(@Valid @RequestBody TaskRequestDto taskDto, Authentication authentication) {
        Task task = taskMapper.toEntity(taskDto);
        String userName = authentication.getName();
        User user = userService.getUserByEmail(userName);
        task.setUser(user);
        return taskMapper.toDto(service.addTask(task));
    }

}
