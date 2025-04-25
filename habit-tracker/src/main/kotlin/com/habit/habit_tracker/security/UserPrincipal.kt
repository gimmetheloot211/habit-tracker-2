package com.habit.habit_tracker.security

import com.habit.habit_tracker.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(private val user: User) : UserDetails {
    fun getUser(): User = user
    override fun getUsername() = user.username
    override fun getPassword() = user.password
    override fun getAuthorities() = emptyList<GrantedAuthority>()
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
    companion object {
        fun create(user: User): UserPrincipal {
            return UserPrincipal(user)
        }
    }
}