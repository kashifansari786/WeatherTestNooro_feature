package com.kashif.weathertestnooro.data.model

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)