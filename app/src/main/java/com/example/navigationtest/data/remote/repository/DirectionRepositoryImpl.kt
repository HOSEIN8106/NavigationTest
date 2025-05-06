package com.example.navigationtest.data.remote.repository

import com.example.navigationtest.data.mapper.toDomain
import com.example.navigationtest.data.remote.api.DirectionApiService
import com.example.navigationtest.domain.models.Direction
import com.example.navigationtest.domain.models.RouteRequest
import com.example.navigationtest.domain.repository.DirectionRepository

class DirectionRepositoryImpl(
    private val api: DirectionApiService
) : DirectionRepository {

    override suspend fun getDirections(request: RouteRequest): Direction {
        val response = api.getDirections(origin = request.origin, destination = request.destination)

        // بررسی وجود featureها
        if (response.features.isNullOrEmpty()) {
            throw IllegalStateException("No features found from API.")
        }

        return response.toDomain() // تبدیل به مدل domain
    }
}
