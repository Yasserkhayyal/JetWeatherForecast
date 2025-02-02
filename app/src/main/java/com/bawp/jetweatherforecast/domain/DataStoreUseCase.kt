package com.bawp.jetweatherforecast.domain

import com.bawp.jetweatherforecast.domain.repository.DataStoreRepository
import com.bawp.jetweatherforecast.model.Favorite
import com.bawp.jetweatherforecast.model.locations.GeoLocationUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DataStoreUseCase {
    val selectedLocationFlow: Flow<GeoLocationUiModel>
    suspend fun storeSelectedLocation(geoLocationUiModel: GeoLocationUiModel)
    suspend fun storeSelectedFavoriteLocation(favorite: Favorite)
}

class DataStoreUseCaseImp @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : DataStoreUseCase {
    override val selectedLocationFlow: Flow<GeoLocationUiModel> =
        dataStoreRepository.selectedLocationFlow.map {
            GeoLocationUiModel(
                locationQuery = it.locationQuery,
                lat = it.lat,
                lon = it.lon
            )
        }

    override suspend fun storeSelectedLocation(geoLocationUiModel: GeoLocationUiModel) =
        dataStoreRepository.storeSelectedLocation(geoLocationUiModel)

    override suspend fun storeSelectedFavoriteLocation(favorite: Favorite) =
        dataStoreRepository.storeSelectedFavoriteLocation(favorite)

}