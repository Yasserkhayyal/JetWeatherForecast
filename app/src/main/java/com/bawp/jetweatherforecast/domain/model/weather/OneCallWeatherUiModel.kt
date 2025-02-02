package com.bawp.jetweatherforecast.domain.model.weather

import com.bawp.jetweatherforecast.data.model.weather.OneCallWeather

data class OneCallWeatherUiModel(
    val current: CurrentUiModel? = null,
    val daily: List<DailyUiModel>? = null
) {
    data class CurrentUiModel(
        val clouds: Int? = null, // 53
        val dewPoint: Double? = null, // 290.69
        val dt: Int? = null, // 1684929490
        val feelsLike: Double? = null, // 292.87
        val humidity: Int? = null, // 89
        val pressure: Int? = null, // 1014
        val sunrise: Int? = null, // 1684926645
        val sunset: Int? = null, // 1684977332
        val temp: Double? = null, // 292.55
        val uvi: Double? = null, // 0.16
        val visibility: Int? = null, // 10000
        val weather: List<WeatherUiModel>? = null,
        val windDeg: Int? = null, // 93
        val windGust: Double? = null, // 6.71
        val windSpeed: Double? = null // 3.13
    )

    data class DailyUiModel(
        val clouds: Int? = null, // 92
        val dewPoint: Double? = null, // 290.48
        val dt: Int? = null, // 1684951200
        val feelsLike: FeelsLikeUiModel? = null,
        val humidity: Int? = null, // 59
        val moonPhase: Double? = null, // 0.16
        val moonrise: Int? = null, // 1684941060
        val moonset: Int? = null, // 1684905480
        val pop: Double? = null, // 0.47
        val pressure: Int? = null, // 1016
        val rain: Double? = null, // 0.15
        val summary: String? = null, // Expect a day of partly cloudy with rain
        val sunrise: Int? = null, // 1684926645
        val sunset: Int? = null, // 1684977332
        val temp: TempUiModel? = null,
        val uvi: Double? = null, // 9.23
        val weather: List<OneCallWeather.Weather>? = null,
        val windDeg: Int? = null, // 76
        val windGust: Double? = null, // 8.92
        val windSpeed: Double? = null // 3.98
    ) {
        data class FeelsLikeUiModel(
            val day: Double? = null, // 299.21
            val eve: Double? = null, // 297.86
            val morn: Double? = null, // 292.87
            val night: Double? = null // 291.37
        )

        data class TempUiModel(
            val day: Double? = null, // 299.03
            val eve: Double? = null, // 297.51
            val max: Double? = null, // 300.35
            val min: Double? = null, // 290.69
            val morn: Double? = null, // 292.55
            val night: Double? = null // 291.45
        )
    }

    data class WeatherUiModel(
        val description: String? = null, // broken clouds
        val icon: String? = null, // 04n
        val id: Int? = null, // 803
        val main: String? = null // Clouds
    )
}