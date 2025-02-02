package com.bawp.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bawp.jetweatherforecast.data.model.favorite.Favorite
import com.bawp.jetweatherforecast.data.model.favorite.Unit

@Database(entities = [Favorite::class, Unit::class], version = 4, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}