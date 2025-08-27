package com.example.to_do_list_api.service;

import com.example.to_do_list_api.domain.auth.UserRepository;
import com.example.to_do_list_api.domain.exceptions.EmailExistsException;
import com.example.to_do_list_api.domain.exceptions.ResourceNotFoundException;
import com.example.to_do_list_api.persistence.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistsException("User with this email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " was not found"));
    }
}
