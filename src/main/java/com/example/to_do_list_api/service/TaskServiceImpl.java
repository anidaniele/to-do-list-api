package com.example.to_do_list_api.service;

import com.example.to_do_list_api.domain.exceptions.ResourceNotFoundException;
import com.example.to_do_list_api.persistence.Task;
import com.example.to_do_list_api.domain.TaskRepository;
import com.example.to_do_list_api.persistence.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Override
    public Task getTaskByIdForUser(int id, User user) {
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " was not found or user doesn't have authorization to access it"));
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task with id " + id + " was not found or user doesn't have authorization to access it"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksByUser(User user) {
        return taskRepository.findAllByUser(user);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Task with id " + id + " does not exist or user doesn't have authorization to access it");
    }

}
