package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.models.Direction
import com.example.navigationtest.domain.models.RouteRequest
import com.example.navigationtest.domain.repository.DirectionRepository

class GetDirectionsUseCase(
    private val repository: DirectionRepository
) {
    suspend operator fun invoke(request: RouteRequest): Direction {
        return repository.getDirections(request)
    }
}