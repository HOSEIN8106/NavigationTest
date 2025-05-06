package com.example.navigationtest.data.remote.dto

data class DirectionDto(
    val type: String,
    val features: List<FeatureDto>,
    val bbox: List<Double>
)

data class FeatureDto(
    val type: String,
    val properties: PropertiesDto,
    val geometry: GeometryDto
)

data class PropertiesDto(
    val summary: SummaryDto
)

data class SummaryDto(
    val distance: Double,
    val duration: Double
)

data class GeometryDto(
    val coordinates: List<List<Double>>, // مختصات مسیر (longitude, latitude)
    val type: String
)

