package com.bawp.jetweatherforecast.domain.repository

import com.bawp.jetweatherforecast.data.model.DataOrException
import com.bawp.jetweatherforecast.data.model.weather.OneCallWeather

interface WeatherRepository {
    suspend fun getWeather(lat: String, lon: String, units: String)
            : DataOrException<OneCallWeather, Boolean, Exception>
}
