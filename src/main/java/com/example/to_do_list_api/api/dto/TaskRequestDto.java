package com.example.to_do_list_api.api.dto;

import com.example.to_do_list_api.domain.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TaskRequestDto(

        @NotBlank(message = "Title must not blank")
        @Schema(description = "Title of the task")
        String title,

        @Schema(description = "Status of the task", example = "TO_DO", allowableValues = {"TO_DO", "IN_PROGRESS", "ON_HOLD", "DONE"})
        Status status
) {}
