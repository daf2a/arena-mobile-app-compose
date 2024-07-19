package com.arena.domain.model

data class RegisterRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val role: String
)

data class LoginRequest(
    val username_email: String,
    val password: String
)
