package com.example.navigationtest.data.remote.repository

import com.example.navigationtest.data.mapper.toDomain
import com.example.navigationtest.data.remote.api.DirectionApiService
import com.example.navigationtest.data.remote.dto.DirectionRequestBody
import com.example.navigationtest.domain.models.Direction
import com.example.navigationtest.domain.repository.DirectionRepository

class DirectionRepositoryImpl(
    private val api: DirectionApiService
) : DirectionRepository {
    override suspend fun getDirections(origin: String, destination: String): Direction {
        val originCoord = origin.split(",").map { it.trim().toDouble() }
        val destCoord = destination.split(",").map { it.trim().toDouble() }

        val body = DirectionRequestBody(
            coordinates = listOf(
                listOf(originCoord[1], originCoord[0]), // lon, lat
                listOf(destCoord[1], destCoord[0])
            )
        )

        return api.getDirections("5b3ce3597851110001cf6248f63220a47a0c4faca61fe8afd2fa0629", body).toDomain()
    }
}