package com.example.navigationtest.data.remote.api

import com.example.navigationtest.data.remote.dto.DirectionDto
import com.example.navigationtest.data.remote.dto.DirectionRequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface DirectionApiService {
    @POST("v2/directions/driving-car")
    suspend fun getDirections(
        @Header("Authorization") apiKey: String,
        @Body body: DirectionRequestBody
    ): DirectionDto
}