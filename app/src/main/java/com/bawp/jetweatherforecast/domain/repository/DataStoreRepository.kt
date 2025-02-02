package com.bawp.jetweatherforecast.domain.repository

import com.bawp.jetweatherforecast.GeoLocationStoreModel
import com.bawp.jetweatherforecast.data.model.favorite.Favorite
import com.bawp.jetweatherforecast.domain.model.locations.GeoLocationUiModel
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    val selectedLocationFlow: Flow<GeoLocationStoreModel>
    suspend fun storeSelectedLocation(geoLocationUiModel: GeoLocationUiModel)
    suspend fun storeSelectedFavoriteLocation(favorite: Favorite)
}