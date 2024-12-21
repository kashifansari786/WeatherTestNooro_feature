package com.kashif.weathertestnooro.data.model

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
data class Current(
    val condition: Condition,
    val feelslike_c: Double,
    val gust_kph: Double,
    val humidity: Int,
    val precip_mm: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val uv: Double,
    val vis_km: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val windchill_c: Double,
    val air_quality: AirQuality,
)