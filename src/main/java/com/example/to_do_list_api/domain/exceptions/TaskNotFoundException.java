package com.example.to_do_list_api.domain.exceptions;


public class TaskNotFoundException extends ResourceNotFoundException {

    private static final String MESSAGE_TEMPLATE = "Task with id %d was not found or user doesn't have authorization to access it";
    public TaskNotFoundException(int id) {
        super(String.format(MESSAGE_TEMPLATE, id));
    }
}
