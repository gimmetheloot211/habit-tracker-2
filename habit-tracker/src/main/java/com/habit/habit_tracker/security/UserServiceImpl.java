package com.habit.habit_tracker.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return UserPrincipal.create(user.get());
    }

    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with userId: " + userId);
        }

        return UserPrincipal.create(user.get());
    }
}
