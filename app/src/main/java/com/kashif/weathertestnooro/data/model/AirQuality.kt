package com.kashif.weathertestnooro.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
data class AirQuality(
    val co:Float,
    val no2:Float,
    val o3:Float,
    val so2:Float,
    val pm2_5:Float,
    val pm10:Float,
    @SerializedName("us-epa-index")
    val us_epa_index:Int, //1 means Good ,2 means Moderate ,3 means Unhealthy for sensitive group ,4 means Unhealthy ,5 means Very Unhealthy ,6 means Hazardous
    @SerializedName("gb-defra-index")
    val gb_defra_index:Int
)
