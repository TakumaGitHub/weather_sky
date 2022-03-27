package com.production.takuma.weathersky.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.production.takuma.weathersky.data.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}