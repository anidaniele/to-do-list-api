package com.example.to_do_list_api.api.dto;

import com.example.to_do_list_api.domain.Status;

public record TaskResponseDto(

        int id,
        String title,
        Status status
) {}
