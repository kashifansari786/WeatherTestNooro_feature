package com.kashif.weathertestnooro.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
@Database(entities = [SearchedCity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun cityDao(): CityDao
}