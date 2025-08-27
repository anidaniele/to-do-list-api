package com.example.to_do_list_api.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthResponseDto {
    private final String token;
    private final Long expiresAt;
}
