package com.habit.habit_tracker.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.exception.ApiRequestException;

@Component
public class AuthUtil {

    public User getAuthenticatedUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                !(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal)) {
            throw new ApiRequestException("User not authenticated", HttpStatus.UNAUTHORIZED);
        }

        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
