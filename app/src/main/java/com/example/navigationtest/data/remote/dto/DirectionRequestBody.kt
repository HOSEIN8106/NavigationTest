package com.example.navigationtest.data.remote.dto

data class DirectionRequestBody(
    val coordinates: List<List<Double>> // [ [lon1, lat1], [lon2, lat2] ]
)
