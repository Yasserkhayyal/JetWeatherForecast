package com.bawp.jetweatherforecast.domain.di

import com.bawp.jetweatherforecast.domain.repository.SearchRepository
import com.bawp.jetweatherforecast.domain.usecase.search.SearchUseCase
import com.bawp.jetweatherforecast.domain.usecase.search.SearchUseCaseImp
import com.bawp.jetweatherforecast.domain.mappers.GeoLocationItemUIModelMapper
import com.bawp.jetweatherforecast.domain.mappers.GeoLocationItemUiModelMapperImp
import com.bawp.jetweatherforecast.data.repository.search.SearchRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface SearchModule {

    @Binds
    fun provideGeolocationItemUiModelMapper(
        geoLocationItemUiModelMapperImp: GeoLocationItemUiModelMapperImp
    ): GeoLocationItemUIModelMapper

    @Binds
    fun providesSearchUseCase(
        searchUseCaseImp: SearchUseCaseImp
    ): SearchUseCase

    @Binds
    fun providesSearchRepository(
        searchRepositoryImp: SearchRepositoryImp
    ): SearchRepository
}