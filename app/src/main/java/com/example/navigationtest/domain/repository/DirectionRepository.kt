package com.example.navigationtest.domain.repository

import com.example.navigationtest.domain.models.Direction

interface DirectionRepository {
    suspend fun getDirections(origin: String, destination: String): Direction
}