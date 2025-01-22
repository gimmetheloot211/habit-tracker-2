package com.habit.habit_tracker.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.dto.user.UserUpdateRequest;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.repository.UserRepository;
import com.habit.habit_tracker.security.JwtProvider;
import com.habit.habit_tracker.security.UserPrincipal;

import static com.habit.habit_tracker.constants.ErrorMessage.*;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserDetails() {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                !(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal)) {
            throw new ApiRequestException(USER_NOT_FOUND, HttpStatus.UNAUTHORIZED);
        }
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        return userPrincipal.getUser();
    }

    public User updateUserDetails(UserUpdateRequest userUpdateRequest) {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                !(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal)) {
            throw new ApiRequestException(USER_NOT_FOUND, HttpStatus.UNAUTHORIZED);
        }
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        User user = userPrincipal.getUser();

        if (userUpdateRequest.getUsername() != null) {
            Optional<User> existingUser = userRepository.findByUsername(userUpdateRequest.getUsername());
            if (existingUser.isPresent()) {
                throw new ApiRequestException(USER_IN_USE, HttpStatus.CONFLICT);
            }
            user.setUsername(userUpdateRequest.getUsername());
        }

        if (userUpdateRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }

        if (userUpdateRequest.getFirstName() != null) {
            user.setFirstName(userUpdateRequest.getFirstName());
        }

        if (userUpdateRequest.getLastName() != null) {
            user.setLastName(userUpdateRequest.getLastName());
        }

        return userRepository.save(user);
    }
}
