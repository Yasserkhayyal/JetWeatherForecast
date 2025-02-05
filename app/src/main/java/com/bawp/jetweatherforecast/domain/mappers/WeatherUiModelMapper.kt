package com.bawp.jetweatherforecast.domain.mappers

import com.bawp.jetweatherforecast.data.model.weather.OneCallWeather
import com.bawp.jetweatherforecast.domain.model.weather.OneCallWeatherUiModel
import javax.inject.Inject

interface WeatherUiModelMapper {
    fun mapToWeatherUiModel(oneCallWeather: OneCallWeather?): OneCallWeatherUiModel?
}

class WeatherUiModelMapperImp @Inject constructor() : WeatherUiModelMapper {
    override fun mapToWeatherUiModel(oneCallWeather: OneCallWeather?): OneCallWeatherUiModel? {
        val oneCallWeatherUiModel = oneCallWeather?.current?.run {
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

        oneCallWeather?.daily?.run {
            oneCallWeatherUiModel?.copy(daily = map {
                OneCallWeatherUiModel.DailyUiModel(
                    clouds = it.clouds,
                    dewPoint = it.dewPoint,
                    dt = it.dt,
                    feelsLike = OneCallWeatherUiModel.DailyUiModel.FeelsLikeUiModel(
                        morn = it.feelsLike?.morn,
                        day = it.feelsLike?.day,
                        eve = it.feelsLike?.eve,
                        night = it.feelsLike?.night
                    ),
                    humidity = it.humidity,
                    moonPhase = it.moonPhase,
                    moonrise = it.moonrise,
                    moonset = it.moonset,
                    pop = it.pop,
                    pressure = it.pressure,
                    rain = it.rain,
                    summary = it.summary,
                    sunrise = it.sunrise,
                    sunset = it.sunset,
                    temp = OneCallWeatherUiModel.DailyUiModel.TempUiModel(
                        morn = it.temp?.morn,
                        day = it.temp?.day,
                        eve = it.temp?.eve,
                        night = it.temp?.night,
                        max = it.temp?.max,
                        min = it.temp?.min
                    ),
                    uvi = it.uvi,
                    weather = it.weather?.map { dayWeather ->
                        OneCallWeatherUiModel.WeatherUiModel(
                            description = dayWeather.description,
                            icon = dayWeather.icon,
                            id = dayWeather.id,
                            main = dayWeather.main,
                        )
                    },
                    windDeg = it.windDeg,
                    windGust = it.windGust,
                    windSpeed = it.windSpeed,
                )
            })
        }
        return oneCallWeatherUiModel
    }
}