package com.habit.habit_tracker.service

import com.habit.habit_tracker.constants.ErrorMessage.USER_IN_USE
import com.habit.habit_tracker.constants.ErrorMessage.USER_NOT_FOUND
import com.habit.habit_tracker.constants.ErrorMessage.INVALID_CREDENTIALS

import com.habit.habit_tracker.domain.User
import com.habit.habit_tracker.dto.auth.AuthResult
import com.habit.habit_tracker.dto.auth.LoginRequest
import com.habit.habit_tracker.dto.auth.RegisterRequest
import com.habit.habit_tracker.exception.ApiRequestException
import com.habit.habit_tracker.repository.UserRepository
import com.habit.habit_tracker.security.JwtProvider
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun registerUser(request: RegisterRequest): AuthResult {
        if (userRepository.findByUsername(request.username).isPresent) {
            throw ApiRequestException(USER_IN_USE, HttpStatus.CONFLICT)
        }

        val newUser = User(
            username = request.username,
            password = passwordEncoder.encode(request.password)
        )

        userRepository.save(newUser)
        
        val token = jwtProvider.createToken(newUser.id!!)

        return AuthResult(token, newUser.username)
    }

    fun loginUser(request: LoginRequest): AuthResult {
        val user = userRepository.findByUsername(request.username)
            .orElseThrow { ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND) }

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw ApiRequestException(INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED)
        }

        val token = jwtProvider.createToken(user.id!!)

        return AuthResult(token, user.username)
    }
}
