package com.kashif.weathertestnooro.data.model

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast?
)

