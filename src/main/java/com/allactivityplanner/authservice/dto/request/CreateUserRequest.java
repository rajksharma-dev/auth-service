package com.allactivityplanner.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        Long organizationId,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Password is required")
        String password,
        @Email(message = "Invalid email")
        String email,
        String phone,
        String role) {
}
