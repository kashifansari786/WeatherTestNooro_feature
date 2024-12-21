package com.kashif.weathertestnooro.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.weathertestnooro.data.model.WeatherResponse
import com.kashif.weathertestnooro.data.remote.WeatherRepository
import com.kashif.weathertestnooro.room.SearchedCity
import com.kashif.weathertestnooro.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository:WeatherRepository): ViewModel() {

    // StateFlow to manage the weather state (Success, Error, Loading)
    private val _weatherState = MutableStateFlow<Resources<WeatherResponse>?>(null)
    val weatherState: StateFlow<Resources<WeatherResponse>?> = _weatherState

    private val _lastSavedCity = MutableStateFlow<String?>(null)
    
    val saveSearchedCity: StateFlow<List<SearchedCity>> = repository.getSavedCity()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Flow to observe the saved city from the repository
    val savedCity = repository.getCity().distinctUntilChanged() // Emit only if the city actually changes

    init {
        viewModelScope.launch {
            savedCity.collect { city ->
                if (city != null && city != _lastSavedCity.value) {
                    _lastSavedCity.value = city
                    loadWeather(city)
                }
            }
        }
    }
    // Function to load weather data for a specific city
    fun loadWeather(city: String) = viewModelScope.launch {
        _weatherState.value = Resources.Loading()
        val weatherResponse = repository.getWeather(city)

        if (weatherResponse is Resources.Success) {
            Log.d("inside_homesc","inside success")
            val searchedData = weatherResponse.data?.let {
                SearchedCity(
                    cityName = it.location.name,
                    cityTemp = it.current.temp_c,
                    weatherIcon = it.current.condition.icon
                )
            }
            searchedData?.let {
                val isCityAlreadySearched = repository.isCityAlreadySearched(it.cityName)
                if (!isCityAlreadySearched) {
                    repository.saveSearchedCity(it)
                }
            }
        }

        repository.saveCity(city)
        _weatherState.value = weatherResponse
    }
    fun savedCity(city:String){
        repository.saveCity(city)
    }

    fun deleteSearchCity(city: SearchedCity) = viewModelScope.launch {
        repository.deleteSelectedCity(city)
    }
}