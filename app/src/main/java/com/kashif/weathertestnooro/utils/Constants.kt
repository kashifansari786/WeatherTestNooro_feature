package com.kashif.weathertestnooro.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.kashif.weathertestnooro.data.model.Forecastday
import com.kashif.weathertestnooro.data.model.Hour
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
object Constants {

    // Base URL for the Weather API
    val BASE_URL = "http://api.weatherapi.com/v1/"

    // API key for accessing the Weather API
    val API_KEY = "62cdacafb2a741f0b1b92457241512"

    // Shared preference key for storing the selected city
    val SELECTED_CITY_PREFERENCE = "selected_city_pref"

    // Shared preference name for storing weather-related preferences
    val WEATHER_PREF_NAME = "weather_pref_name"

    // Navigation route constant for Home Screen
    val HOME_SCREEN = "home_screen"

    // Navigation route constant for Search Screen
    val SEARCH_SCREEN = "search_screen"


    @RequiresApi(Build.VERSION_CODES.O)
    fun getFilteredWeatherData(forecastDays: List<Forecastday>): List<Hour> {
        val now = LocalDateTime.now() // Current date and time
        val currentDate = now.toLocalDate().toString() // Today's date in "yyyy-MM-dd" format
        val currentTime = now.toLocalTime() // Current time
        val nextDate = now.plusDays(1).toLocalDate().toString() // Tomorrow's date

        // Filter today's data from now until midnight
        val todayData = forecastDays.find { it.date == currentDate }?.hour?.filter {
            val time = LocalDateTime.parse(it.time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            time.toLocalTime().isAfter(currentTime)
        } ?: emptyList()

        // Filter next day's data from midnight until now's time
        val nextDayData = forecastDays.find { it.date == nextDate }?.hour?.filter {
            val time = LocalDateTime.parse(it.time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            time.toLocalTime().isBefore(currentTime)
        } ?: emptyList()

        return todayData + nextDayData
    }

    fun getAirQuality(index:Int):String{
        var quality:String=""
        when(index){
            1->{
                quality="Good Air"
            }
            2->{
               quality= "Moderate Polluted"
            }
            3->{
                quality="Unhealthy For Sensitive People"
            }
            4->{
                quality="Very Unhealthy Air"
            }
            5->{
                quality="Hazardous Air"
            }
            else->{
                quality="Good Air"
            }
        }
        return quality
    }
}