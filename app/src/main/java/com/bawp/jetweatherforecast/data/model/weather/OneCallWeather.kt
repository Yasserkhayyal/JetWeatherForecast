package com.bawp.jetweatherforecast.data.model.weather

data class OneCallWeather(
    val alerts: List<Alert>? = null,
    val current: Current? = null,
    val daily: List<Daily>? = null,
    val hourly: List<Hourly>? = null,
    val lat: Double? = null, // 33.44
    val lon: Double? = null, // -94.04
    val minutely: List<Minutely>? = null,
    val timezone: String? = null, // America/Chicago
    val timezoneOffset: Int? = null // -18000
) {
    data class Alert(
        val description: String? = null, // ...SMALL CRAFT ADVISORY REMAINS IN EFFECT FROM 5 PM THISAFTERNOON TO 3 AM EST FRIDAY...* WHAT...North winds 15 to 20 kt with gusts up to 25 kt and seas3 to 5 ft expected.* WHERE...Coastal waters from Little Egg Inlet to Great EggInlet NJ out 20 nm, Coastal waters from Great Egg Inlet toCape May NJ out 20 nm and Coastal waters from Manasquan Inletto Little Egg Inlet NJ out 20 nm.* WHEN...From 5 PM this afternoon to 3 AM EST Friday.* IMPACTS...Conditions will be hazardous to small craft.
        val end: Int? = null, // 1684988747
        val event: String? = null, // Small Craft Advisory
        val senderName: String? = null, // NWS Philadelphia - Mount Holly (New Jersey, Delaware, Southeastern Pennsylvania)
        val start: Int? = null, // 1684952747
        val tags: List<Any>? = null
    )

    data class Current(
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
        val weather: List<Weather>? = null,
        val windDeg: Int? = null, // 93
        val windGust: Double? = null, // 6.71
        val windSpeed: Double? = null // 3.13
    )

    data class Daily(
        val clouds: Int? = null, // 92
        val dewPoint: Double? = null, // 290.48
        val dt: Int? = null, // 1684951200
        val feelsLike: FeelsLike? = null,
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
        val temp: Temp? = null,
        val uvi: Double? = null, // 9.23
        val weather: List<Weather>? = null,
        val windDeg: Int? = null, // 76
        val windGust: Double? = null, // 8.92
        val windSpeed: Double? = null // 3.98
    ) {
        data class FeelsLike(
            val day: Double? = null, // 299.21
            val eve: Double? = null, // 297.86
            val morn: Double? = null, // 292.87
            val night: Double? = null // 291.37
        )

        data class Temp(
            val day: Double? = null, // 299.03
            val eve: Double? = null, // 297.51
            val max: Double? = null, // 300.35
            val min: Double? = null, // 290.69
            val morn: Double? = null, // 292.55
            val night: Double? = null // 291.45
        )
    }

    data class Hourly(
        val clouds: Int? = null, // 54
        val dewPoint: Double? = null, // 290.51
        val dt: Int? = null, // 1684926000
        val feelsLike: Double? = null, // 292.33
        val humidity: Int? = null, // 91
        val pop: Double? = null, // 0.15
        val pressure: Int? = null, // 1014
        val temp: Double? = null, // 292.01
        val uvi: Int? = null, // 0
        val visibility: Int? = null, // 10000
        val weather: List<Weather?>? = null,
        val windDeg: Int? = null, // 86
        val windGust: Double? = null, // 5.88
        val windSpeed: Double? = null // 2.58
    )

    data class Minutely(
        val dt: Int? = null, // 1684929540
        val precipitation: Int? = null // 0
    )

    data class Weather(
        val description: String? = null, // broken clouds
        val icon: String? = null, // 04n
        val id: Int? = null, // 803
        val main: String? = null // Clouds
    )
}