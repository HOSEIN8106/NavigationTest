package com.example.navigationtest.data.mapper

import com.example.navigationtest.data.remote.dto.DirectionDto
import com.example.navigationtest.domain.models.Direction
import com.example.navigationtest.domain.models.LatLng


fun DirectionDto.toDomain(): Direction {
    val route = features.firstOrNull()?.geometry?.coordinates ?: emptyList()
    return Direction(route.map { LatLng(it[1], it[0]) }) // lat = [1], lon = [0]
}