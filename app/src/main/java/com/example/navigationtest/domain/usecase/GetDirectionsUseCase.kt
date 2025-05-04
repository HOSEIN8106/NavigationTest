package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.models.Direction
import com.example.navigationtest.domain.repository.DirectionRepository

class GetDirectionsUseCase(private val repository: DirectionRepository) {
    suspend operator fun invoke(origin: String, destination: String): Direction {
        return repository.getDirections(origin, destination)
    }
}