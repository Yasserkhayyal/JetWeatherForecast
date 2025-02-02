package com.bawp.jetweatherforecast.data.repository.search

import android.util.Log
import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.domain.repository.SearchRepository
import com.bawp.jetweatherforecast.model.locations.GeoLocationItem
import com.bawp.jetweatherforecast.data.network.WeatherApi
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val api: WeatherApi
) : SearchRepository {
    override suspend fun getGeoLocations(query: String): DataOrException<List<GeoLocationItem>,
            Boolean, Exception> =
        DataOrException<List<GeoLocationItem>, Boolean, Exception>(loading = true).apply {
            try {
                data = api.getGeoLocations(query = query)
            } catch (exception: Exception) {
                Log.e("API ERROR", "getGeoLocations: $e")
                e = exception
            }
            loading = false
        }
}