package com.example.to_do_list_api.api;

import com.example.to_do_list_api.api.dto.AuthRequestDto;
import com.example.to_do_list_api.api.dto.AuthResponseDto;
import com.example.to_do_list_api.api.dto.UserRequestDto;
import com.example.to_do_list_api.api.dto.UserResponseDto;
import com.example.to_do_list_api.api.mappers.UserMapper;
import com.example.to_do_list_api.service.AuthService;
import com.example.to_do_list_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/login")
    public AuthResponseDto authorize(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }

    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto userRequestDto) {
        return mapper.toDto(userService.addUser(mapper.toEntity(userRequestDto)));
    }
}

