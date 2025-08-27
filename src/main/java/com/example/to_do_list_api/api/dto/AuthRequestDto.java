package com.example.to_do_list_api.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {

    private String email;
    private String password;
}
