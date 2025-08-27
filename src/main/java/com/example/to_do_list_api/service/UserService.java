package com.example.to_do_list_api.service;

import com.example.to_do_list_api.persistence.User;

public interface UserService {

    User addUser (User user);

    User getUserByEmail(String email);
}
