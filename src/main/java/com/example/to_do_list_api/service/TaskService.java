package com.example.to_do_list_api.service;


import com.example.to_do_list_api.persistence.Task;
import com.example.to_do_list_api.persistence.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Task getTaskByIdForUser(int id, User user);

    Task getTaskById(int id);

    Page<Task> getAllTasksByUser(User user, Pageable pageable);

    Task addTask(Task task);

    void deleteTaskById(int id, User user);



}
