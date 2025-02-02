package com.bawp.jetweatherforecast.domain

import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.model.locations.GeoLocationItem

interface SearchRepository {
    suspend fun getGeoLocations(query: String): DataOrException<List<GeoLocationItem>,
            Boolean, Exception>
}