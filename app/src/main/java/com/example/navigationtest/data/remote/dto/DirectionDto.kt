package com.example.navigationtest.data.remote.dto

data class DirectionDto(
    val features: List<Feature>
)

data class Feature(
    val geometry: Geometry
)

data class Geometry(
    val coordinates: List<List<Double>>
)
