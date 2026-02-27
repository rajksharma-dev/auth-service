package com.allactivityplanner.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        Long id,
        Long organizationId,
        @NotBlank(message = "Name is required")
        String name,
        @Email(message = "Invalid email")
        String email,
        String role) {

}
