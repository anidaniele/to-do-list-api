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
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Override
    public Task getTaskByIdForUser(int id, User user) {
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException(id));
    }


    @Override
    public Page<Task> getAllTasksByUser(User user, Pageable pageable) {
        return taskRepository.findAllByUser(user, pageable);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(int id, User user) {
        Optional<Task> task = taskRepository.findByIdAndUser(id, user);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        }
        else throw new TaskNotFoundException(id);
    }

}
