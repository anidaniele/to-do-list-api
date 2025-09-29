package com.example.to_do_list_api.service;

import com.example.to_do_list_api.domain.exceptions.TaskNotFoundException;
import com.example.to_do_list_api.persistence.Task;
import com.example.to_do_list_api.domain.TaskRepository;
import com.example.to_do_list_api.persistence.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task getTaskByIdForUser(int id, User user) {
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException(id));
    }

    public Page<Task> getAllTasksByUser(User user, Pageable pageable) {
        return taskRepository.findAllByUser(user, pageable);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        }
        else throw new TaskNotFoundException(id);
    }
}
