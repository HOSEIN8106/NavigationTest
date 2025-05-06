package com.example.navigationtest.data.remote.api

import com.example.navigationtest.data.remote.dto.DirectionDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionApiService {
    @GET("v2/directions/driving-car")
    suspend fun getDirections(
        @Query("start") origin: String,
        @Query("end") destination: String,
        @Query("api_key") apiKey: String = API_KEY
    ): DirectionDto

    companion object {
        const val API_KEY = "5b3ce3597851110001cf6248f63220a47a0c4faca61fe8afd2fa0629"
    }
}