package com.allactivityplanner.authservice.service;

import com.allactivityplanner.authservice.dto.request.CreateUserRequest;
import com.allactivityplanner.authservice.dto.response.UserResponse;
import com.allactivityplanner.authservice.model.User;
import com.allactivityplanner.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(CreateUserRequest request) {

        User user = new User(
                request.organizationId(),
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.phone(),
                request.role(),
                true
        );

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
