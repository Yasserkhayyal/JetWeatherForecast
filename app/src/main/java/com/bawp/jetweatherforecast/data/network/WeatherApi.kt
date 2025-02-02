package com.bawp.jetweatherforecast.data.network

import com.bawp.jetweatherforecast.data.model.locations.GeoLocationItem
import com.bawp.jetweatherforecast.data.model.weather.OneCallWeather
import com.bawp.jetweatherforecast.presentation.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "geo/1.0/direct")
    suspend fun getGeoLocations(
        @Query("q") query: String,
        @Query("limit") limit: String = "5",
        @Query("appid") appid: String = API_KEY // your api key
    ): List<GeoLocationItem>

    @GET(value = "data/3.0/onecall")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String = "minutely,hourly,alerts",
        @Query("units") units: String = "imperial",
        @Query("lang") lang: String = "en",
        @Query("appid") appid: String = API_KEY // your api key
    ): OneCallWeather
}