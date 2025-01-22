package com.habit.habit_tracker.security;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends AuthenticationException {

    private HttpStatus httpStatus;

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
