package com.bawp.jetweatherforecast.data.repository.weather

import android.util.Log
import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.domain.repository.WeatherRepository
import com.bawp.jetweatherforecast.model.weather.OneCallWeather
import com.bawp.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRepository {
    override suspend fun getWeather(lat: String, lon: String, units: String)
            : DataOrException<OneCallWeather, Boolean, Exception> =
        DataOrException<OneCallWeather, Boolean, Exception>(loading = true).apply {
            try {
                data = weatherApi.getWeather(lat = lat, lon = lon, units = units)
            } catch (exception: Exception) {
                Log.d("API ERROR", "getWeather: $e")
                e = exception
            }
            loading = false
        }
}