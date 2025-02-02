package com.bawp.jetweatherforecast.domain.usecase.weather

import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.domain.repository.WeatherRepository
import com.bawp.jetweatherforecast.mappers.WeatherUiModelMapper
import com.bawp.jetweatherforecast.model.weather.OneCallWeatherUiModel
import javax.inject.Inject

interface WeatherDataUseCase {
    suspend fun getWeather(lat: String, lon: String, units: String)
            : DataOrException<OneCallWeatherUiModel, Boolean, Exception>
}

class WeatherDataUseCaseImp @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherUiModelMapper: WeatherUiModelMapper
) : WeatherDataUseCase {
    override suspend fun getWeather(
        lat: String,
        lon: String,
        units: String
    ): DataOrException<OneCallWeatherUiModel, Boolean, Exception> =
        weatherRepository.getWeather(lat = lat, lon = lon, units = units)
            .run {
                DataOrException(
                    data = weatherUiModelMapper.mapToWeatherUiModel(data),
                    loading = loading,
                    e = e
                )
            }
}