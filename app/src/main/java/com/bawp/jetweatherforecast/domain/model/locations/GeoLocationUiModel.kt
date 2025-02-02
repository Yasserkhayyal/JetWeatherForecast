package com.bawp.jetweatherforecast.domain.model.locations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeoLocationUiModel(
    val locationQuery: String = "",
    val lat: String = "",
    val lon: String = ""
) : Parcelable

fun GeoLocationUiModel.isNotEmpty(): Boolean = locationQuery.isNotEmpty()
        && lat.isNotEmpty() && lon.isNotEmpty()
