package com.kashif.weathertestnooro.data.remote

import com.kashif.weathertestnooro.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
interface WeatherApiService {

    @GET("forecast.json")
    suspend fun getCurrentWeatherData(
        @Query("key") apiKey: String,
        @Query("days") days:Int,
        @Query("aqi") aqi:String,
        @Query("q") city: String
    ): WeatherResponse
}