package com.example.navigationtest.domain.repository

import com.example.navigationtest.domain.models.Direction
import com.example.navigationtest.domain.models.RouteRequest

interface DirectionRepository {
    suspend fun getDirections(request: RouteRequest): Direction
}