package com.arena.domain.model

data class RegisterResponse(
    val code: String,
    val message: String,
    val status: String
)

data class LoginResponse(
    val message: String,
    val status: String,
    val token: String,
    val role: String
)