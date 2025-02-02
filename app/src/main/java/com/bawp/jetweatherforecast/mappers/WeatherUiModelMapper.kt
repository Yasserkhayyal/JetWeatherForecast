package com.bawp.jetweatherforecast.mappers

import com.bawp.jetweatherforecast.model.weather.OneCallWeather
import com.bawp.jetweatherforecast.model.weather.OneCallWeatherUiModel
import javax.inject.Inject

interface WeatherUiModelMapper {
    fun mapToWeatherUiModel(oneCallWeather: OneCallWeather?): OneCallWeatherUiModel?
}

class WeatherUiModelMapperImp @Inject constructor() : WeatherUiModelMapper {
    override fun mapToWeatherUiModel(oneCallWeather: OneCallWeather?) =
        oneCallWeather?.current?.run {
            OneCallWeatherUiModel(
                current = OneCallWeatherUiModel.CurrentUiModel(
                    clouds = clouds,
                    dewPoint = dewPoint,
                    dt = dt,
                    feelsLike = feelsLike,
                    humidity = humidity,
                    pressure = pressure,
                    sunrise = sunrise,
                    sunset = sunset,
                    temp = temp,
                    uvi = uvi,
                    visibility = visibility,
                    weather = weather?.map {
                        OneCallWeatherUiModel.WeatherUiModel(
                            description = it.description,
                            icon = it.icon,
                            id = it.id,
                            main = it.main,
                        )
                    },
                    windDeg = windDeg,
                    windGust = windGust,
                    windSpeed = windSpeed
                )
            )
        }
}