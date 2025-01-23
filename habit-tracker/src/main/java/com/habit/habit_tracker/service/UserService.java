package com.habit.habit_tracker.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.dto.user.UserUpdateRequest;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.repository.UserRepository;
import com.habit.habit_tracker.security.AuthUtil;

import static com.habit.habit_tracker.constants.ErrorMessage.*;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthUtil authUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUtil = authUtil;
    }

    public User getUserDetails() {
        return authUtil.getAuthenticatedUser();
    }

    public User updateUserDetails(UserUpdateRequest userUpdateRequest) {
        User user = authUtil.getAuthenticatedUser();

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
