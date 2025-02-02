package com.bawp.jetweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.domain.usecase.datastore.DataStoreUseCase
import com.bawp.jetweatherforecast.domain.usecase.weather.WeatherDataUseCase
import com.bawp.jetweatherforecast.model.locations.GeoLocationUiModel
import com.bawp.jetweatherforecast.model.weather.OneCallWeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherDataUseCase: WeatherDataUseCase,
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {
    private val _weatherData: MutableStateFlow<DataOrException<OneCallWeatherUiModel, Boolean, Exception>> =
        MutableStateFlow(value = DataOrException())
    val weatherData = _weatherData

    val selectedLocationFlow: Flow<GeoLocationUiModel> = dataStoreUseCase.selectedLocationFlow

    suspend fun getWeatherData(lat: String, lon: String, units: String) {
        _weatherData.value = weatherDataUseCase.getWeather(lat = lat, lon = lon, units = units)
    }
}