package com.bawp.jetweatherforecast.domain.mappers

import com.bawp.jetweatherforecast.data.model.locations.GeoLocationItem
import com.bawp.jetweatherforecast.domain.model.locations.GeoLocationUiModel
import javax.inject.Inject

interface GeoLocationItemUIModelMapper {
    fun mapToGeoLocationUiModel(geoLocationItem: GeoLocationItem): GeoLocationUiModel
}

class GeoLocationItemUiModelMapperImp @Inject constructor() : GeoLocationItemUIModelMapper {
    override fun mapToGeoLocationUiModel(geoLocationItem: GeoLocationItem): GeoLocationUiModel {
        val location = buildString {
            with(geoLocationItem) {
                append(name)
                state?.let { append(", $it") }
                country?.let { append(", $it") }
            }
        }
        return GeoLocationUiModel(
            locationQuery = location,
            lat = geoLocationItem.lat.toString(),
            lon = geoLocationItem.lon.toString()
        )
    }
}


