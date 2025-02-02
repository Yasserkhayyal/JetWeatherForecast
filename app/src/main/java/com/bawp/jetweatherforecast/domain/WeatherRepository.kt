package com.bawp.jetweatherforecast.domain

import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.model.weather.OneCallWeather

interface WeatherRepository {
    suspend fun getWeather(lat: String, lon: String, units: String)
            : DataOrException<OneCallWeather, Boolean, Exception>
}
