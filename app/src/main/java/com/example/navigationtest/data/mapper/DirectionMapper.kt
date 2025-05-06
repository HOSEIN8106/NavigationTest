package com.example.navigationtest.data.mapper

import com.example.navigationtest.data.remote.dto.DirectionDto
import com.example.navigationtest.domain.models.Direction
import org.maplibre.geojson.Point


fun DirectionDto.toDomain(): Direction {
    val feature = this.features.firstOrNull()
        ?: throw IllegalArgumentException("No features found in the response.")

    val geometry = feature.geometry
    val coordinates = geometry.coordinates.flatten() // تبدیل مختصات به یک لیست مسطح از مختصات
    val points = coordinates.chunked(2).map {
        Point.fromLngLat(it[0], it[1]) // تبدیل هر جفت مختصات (longitude, latitude) به یک Point
    }

    return Direction(points) // برگرداندن Direction
}
