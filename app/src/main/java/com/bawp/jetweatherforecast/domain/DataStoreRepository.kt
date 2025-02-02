package com.bawp.jetweatherforecast.domain

import com.bawp.jetweatherforecast.GeoLocationStoreModel
import com.bawp.jetweatherforecast.model.Favorite
import com.bawp.jetweatherforecast.model.locations.GeoLocationUiModel
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    val selectedLocationFlow: Flow<GeoLocationStoreModel>
    suspend fun storeSelectedLocation(geoLocationUiModel: GeoLocationUiModel)
    suspend fun storeSelectedFavoriteLocation(favorite: Favorite)
}