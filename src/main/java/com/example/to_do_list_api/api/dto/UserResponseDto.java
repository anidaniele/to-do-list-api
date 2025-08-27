package com.example.to_do_list_api.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    int id;
    private String name;
    private String email;
    private String password;
}
