package com.arena.data.repository

import android.util.Log
import com.arena.data.network.ApiService
import com.arena.domain.model.LoginRequest
import com.arena.domain.model.LoginResponse
import com.arena.domain.model.RegisterRequest
import com.arena.domain.model.RegisterResponse
import retrofit2.Response

class AuthRepository(private val apiService: ApiService) {

    suspend fun register(name: String, username: String, email: String, password: String, role: Int): Response<RegisterResponse> {
        val roleString = role.toString()
        Log.d("AuthRepository", "Registering user: name=$name, username=$username, email=$email, role=$roleString")
        val response = apiService.register(RegisterRequest(name, username, email, password, roleString))
        Log.d("AuthRepository", "Register response: $response")
        return response
    }

    suspend fun login(usernameEmail: String, password: String): Response<LoginResponse> {
        Log.d("AuthRepository", "Logging in: usernameEmail=$usernameEmail")
        val response = apiService.login(LoginRequest(usernameEmail, password))
        Log.d("AuthRepository", "Login response: $response")
        return response
    }
}
