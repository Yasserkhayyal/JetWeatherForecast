package com.bawp.jetweatherforecast.domain.repository

import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.data.model.locations.GeoLocationItem

interface SearchRepository {
    suspend fun getGeoLocations(query: String): DataOrException<List<GeoLocationItem>,
            Boolean, Exception>
}