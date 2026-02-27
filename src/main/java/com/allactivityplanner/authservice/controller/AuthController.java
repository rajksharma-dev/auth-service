package com.allactivityplanner.authservice.controller;

import com.allactivityplanner.authservice.config.JwtService;
import com.allactivityplanner.authservice.dto.request.CreateUserRequest;
import com.allactivityplanner.authservice.dto.request.LoginRequest;
import com.allactivityplanner.authservice.dto.response.LoginResponse;
import com.allactivityplanner.authservice.dto.response.UserResponse;
import com.allactivityplanner.authservice.model.User;
import com.allactivityplanner.authservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPathVersions.V1 + "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody CreateUserRequest createUserRequest) {
        return userService.register(createUserRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userService.findByEmail(request.email()).get();

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user.getEmail(),
                user.getRole(),
                user.getOrganizationId()
        );
    }
}

