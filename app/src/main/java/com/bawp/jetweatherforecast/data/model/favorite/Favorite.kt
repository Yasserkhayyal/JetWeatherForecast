package com.bawp.jetweatherforecast.data.model.favorite

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl")
data class Favorite(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "locationQuery")
    val locationQuery: String,

    @ColumnInfo(name = "lat")
    val lat: String,

    @ColumnInfo(name = "lon")
    val lon: String
)
