package com.bawp.jetweatherforecast.domain.usecase.search

import com.bawp.jetweatherforecast.data.model.DataOrException
import com.bawp.jetweatherforecast.domain.repository.SearchRepository
import com.bawp.jetweatherforecast.domain.mappers.GeoLocationItemUIModelMapper
import com.bawp.jetweatherforecast.domain.model.locations.GeoLocationUiModel
import javax.inject.Inject

interface SearchUseCase {
    suspend fun getGeoLocations(query: String): DataOrException<List<GeoLocationUiModel>, Boolean, Exception>
}

class SearchUseCaseImp @Inject constructor(
    private val mapper: GeoLocationItemUIModelMapper,
    private val repository: SearchRepository
) : SearchUseCase {
    override suspend fun getGeoLocations(query: String): DataOrException<List<GeoLocationUiModel>, Boolean, Exception> =
        with(repository.getGeoLocations(query)) {
            DataOrException(
                data = data?.map { mapper.mapToGeoLocationUiModel(it) },
                loading = loading,
                e = e
            )
        }
}