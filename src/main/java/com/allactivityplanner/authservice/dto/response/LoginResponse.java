package com.allactivityplanner.authservice.dto.response;

public record LoginResponse(
        String token,
        String email,
        String role,
        Long orgId
) {
}

