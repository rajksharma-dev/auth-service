package com.allactivityplanner.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "Invalid email")
        String email,

        @NotBlank
        String password) {
}
