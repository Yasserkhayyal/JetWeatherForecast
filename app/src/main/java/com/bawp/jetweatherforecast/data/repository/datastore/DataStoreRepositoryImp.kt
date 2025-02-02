package com.bawp.jetweatherforecast.data.repository.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.bawp.jetweatherforecast.GeoLocationStoreModel
import com.bawp.jetweatherforecast.data.repository.search.SearchRepositoryImp
import com.bawp.jetweatherforecast.domain.repository.DataStoreRepository
import com.bawp.jetweatherforecast.data.model.favorite.Favorite
import com.bawp.jetweatherforecast.domain.model.locations.GeoLocationUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEmpty
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImp @Inject constructor(
    private val dataStore: DataStore<GeoLocationStoreModel>
) : DataStoreRepository {
    override val selectedLocationFlow: Flow<GeoLocationStoreModel> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.d(
                    SearchRepositoryImp::class.java.simpleName,
                    "failed to read stored selected geo location:" +
                            " ${exception.cause?.message ?: exception.message}}"
                )
                emit(GeoLocationStoreModel.getDefaultInstance())
            } else {
                throw exception
            }
        }.onEmpty { emit(GeoLocationStoreModel.getDefaultInstance()) }

    override suspend fun storeSelectedLocation(geoLocationUiModel: GeoLocationUiModel) {
        dataStore.updateData { currentSavedLocation ->
            currentSavedLocation.toBuilder().setLocationQuery(geoLocationUiModel.locationQuery)
                .setLat(geoLocationUiModel.lat).setLon(geoLocationUiModel.lon).build()
        }
    }

    override suspend fun storeSelectedFavoriteLocation(favorite: Favorite) {
        dataStore.updateData { currentSavedLocation ->
            currentSavedLocation.toBuilder().setLocationQuery(favorite.locationQuery)
                .setLat(favorite.lat).setLon(favorite.lon).build()
        }
    }
}