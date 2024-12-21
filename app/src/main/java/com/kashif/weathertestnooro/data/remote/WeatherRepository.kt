package com.kashif.weathertestnooro.data.remote

import android.util.Log
import com.kashif.weathertestnooro.data.local.CityPreferences
import com.kashif.weathertestnooro.data.model.WeatherResponse
import com.kashif.weathertestnooro.room.CityDao
import com.kashif.weathertestnooro.room.SearchedCity
import com.kashif.weathertestnooro.utils.Constants.API_KEY
import com.kashif.weathertestnooro.utils.Resources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
// WeatherRepository class handles fetching weather data and managing city preferences
class WeatherRepository @Inject constructor(
    private val apiService: WeatherApiService, // API service to fetch weather data from the network
    private val cityDao: CityDao,           //Local database storage for saving and retrieving the city
    private val cityPreference: CityPreferences // Local storage for saving and retrieving the city
) {

    // Fetch weather data for a given city
    suspend fun getWeather(city:String): Resources<WeatherResponse> {
        return try {
            // Make a network request to fetch weather data
            val response= apiService.getCurrentWeatherData(API_KEY,7,"yes",city)
            // If successful, return the weather data wrapped in a Resources.Success object
            Resources.Success(response)
        }catch (e:Exception){
            // If an error occurs, return an error message wrapped in a Resources.Error object
            Resources.Error("Failed to fetch weather : ${e.message}")
        }
    }

    // Save the city to local storage asynchronously using a coroutine
    fun saveCity(city: String) {
        // Launching a coroutine on the IO dispatcher for background work
        CoroutineScope(Dispatchers.IO).launch {
            cityPreference.saveCity(cityName = city) // Saving the city using the cityPreference instance
        }
    }

    // Retrieve the saved city as a Flow
    fun getCity(): Flow<String?> {
        // Fetch the stored city from cityPreference as a Flow
        return cityPreference.city
    }

    suspend fun saveSearchedCity(city: SearchedCity){
            cityDao.insertCity(city)

    }
    fun getSavedCity():Flow<List<SearchedCity>> = cityDao.getAllCities()


    suspend fun deleteSelectedCity(city: SearchedCity){
        cityDao.deleteCity(city)
    }

    // Check if a city is already searched
    suspend fun isCityAlreadySearched(cityName: String): Boolean {
        return cityDao.getCityByName(cityName) != null
    }


}