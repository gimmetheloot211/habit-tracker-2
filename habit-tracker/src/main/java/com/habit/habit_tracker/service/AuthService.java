package com.habit.habit_tracker.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.dto.auth.LoginRequest;
import com.habit.habit_tracker.dto.auth.RegisterRequest;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.repository.UserRepository;
import com.habit.habit_tracker.security.JwtProvider;

import static com.habit.habit_tracker.constants.ErrorMessage.*;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String registerUser(RegisterRequest registerRequest) {
        Optional<User> existingUser = userRepository.findByUsername(registerRequest.getUsername());
        if (existingUser.isPresent()) {
            throw new ApiRequestException(USER_IN_USE, HttpStatus.CONFLICT);
        }

        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(newUser);

        return jwtProvider.createToken(newUser.getId());
    }

    public String loginUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (!user.isPresent()) {
            throw new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        User foundUser = user.get();

        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword());
        if (!matches) {
            throw new ApiRequestException(INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        }

        return jwtProvider.createToken(foundUser.getId());
    }
}
