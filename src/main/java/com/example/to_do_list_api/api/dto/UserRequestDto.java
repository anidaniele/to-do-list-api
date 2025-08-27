package com.example.to_do_list_api.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "Name must not blank")
    @Schema(description = "User's name")
    private String name;

    @NotBlank(message = "Email must not blank")
    @Schema(description = "User's email")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Entered email address is invalid")
    private String email;

    @NotBlank(message = "Password must not blank")
    @Schema(description = "User's password")
    private String password;
}
