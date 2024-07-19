package com.arena.domain.usecase

import com.arena.data.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username_email: String, password: String) = authRepository.login(username_email, password)
}

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(name: String, username: String, email: String, password: String, role: Int) = authRepository.register(name, username, email, password, role)
}
