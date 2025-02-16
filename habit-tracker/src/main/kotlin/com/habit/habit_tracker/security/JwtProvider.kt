package com.habit.habit_tracker.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtProvider(
    private val userDetailsService: UserDetailsService,
    @Value("\${jwt.secret}") private var secretKey: String,
    @Value("\${jwt.expiration}") private val validityInMilliseconds: Long
) {
    private lateinit var signingKey: Key

    @PostConstruct
    fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        signingKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun createToken(userId: Long): String {
        val claims: MutableMap<String, Any> = HashMap()
        claims["userId"] = userId

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds * 1000)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            println("JWT validation error: ${e.message}")
            false
        }
    }

    fun getUserId(token: String): Long? {
        return Jwts.parserBuilder().setSigningKey(signingKey).build()
            .parseClaimsJws(token).body["userId"] as? Long
    }

    fun getAuthentication(token: String): Authentication? {
        val userId = getUserId(token) ?: return null
        val userDetails = (userDetailsService as? UserServiceImpl)?.loadUserById(userId)
            ?: return null
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken?.startsWith("Bearer ") == true) bearerToken.substring(7) else null
    }
}
