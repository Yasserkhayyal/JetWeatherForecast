package com.bawp.jetweatherforecast.domain.di

import com.bawp.jetweatherforecast.domain.usecase.weather.WeatherDataUseCase
import com.bawp.jetweatherforecast.domain.usecase.weather.WeatherDataUseCaseImp
import com.bawp.jetweatherforecast.domain.repository.WeatherRepository
import com.bawp.jetweatherforecast.domain.mappers.WeatherUiModelMapper
import com.bawp.jetweatherforecast.domain.mappers.WeatherUiModelMapperImp
import com.bawp.jetweatherforecast.data.repository.weather.WeatherRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface MainModule {
    @Binds
    fun bindsWeatherDataUseCase(weatherDataUseCaseImp: WeatherDataUseCaseImp): WeatherDataUseCase

    @Binds
    fun bindsWeatherRepository(weatherRepository: WeatherRepositoryImp): WeatherRepository

    @Binds
    fun bindsWeatherUiModelMapper(
        weatherUiModelMapperImp: WeatherUiModelMapperImp
    ): WeatherUiModelMapper
}