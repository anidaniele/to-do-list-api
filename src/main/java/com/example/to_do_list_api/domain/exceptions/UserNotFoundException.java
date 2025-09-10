package com.example.to_do_list_api.domain.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {

    private static final String MESSAGE_TEMPLATE = "User with email %s was not found";
    public UserNotFoundException(String email) {
        super(String.format(MESSAGE_TEMPLATE, email));
    }
}
