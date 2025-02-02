package com.bawp.jetweatherforecast.data.model.favorite

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bawp.jetweatherforecast.utils.Constants.IMPERIAL

@Entity(tableName = "settings_tbl")
data class Unit(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "unit", defaultValue = IMPERIAL)
    val unit: String
)
