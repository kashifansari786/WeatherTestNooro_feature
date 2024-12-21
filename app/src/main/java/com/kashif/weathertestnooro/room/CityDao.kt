package com.kashif.weathertestnooro.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
@Dao
interface CityDao {

    @Query("SELECT * FROM searchedCities")
    fun getAllCities(): Flow<List<SearchedCity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: SearchedCity)

    @Delete
    suspend fun deleteCity(city: SearchedCity)

    @Query("SELECT * FROM searchedCities WHERE cityName = :cityName LIMIT 1")
    suspend fun getCityByName(cityName: String): SearchedCity?
}