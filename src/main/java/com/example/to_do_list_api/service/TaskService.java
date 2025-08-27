package com.example.to_do_list_api.service;


import com.example.to_do_list_api.persistence.Task;
import com.example.to_do_list_api.persistence.User;

import java.util.List;

public interface TaskService {

    Task getTaskByIdForUser(int id, User user);

    Task getTaskById(int id);

    List<Task> getAllTasks();
    List<Task> getAllTasksByUser(User user);

    Task addTask(Task task);

    void deleteTaskById(int id);



}
