package com.arena.data.network

import com.arena.domain.model.LoginRequest
import com.arena.domain.model.LoginResponse
import com.arena.domain.model.RegisterRequest
import com.arena.domain.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
