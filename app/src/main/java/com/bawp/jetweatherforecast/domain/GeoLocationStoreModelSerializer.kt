package com.bawp.jetweatherforecast.domain

import android.util.Log
import androidx.datastore.core.Serializer
import com.bawp.jetweatherforecast.GeoLocationStoreModel
import java.io.InputStream
import java.io.OutputStream

object GeoLocationStoreModelSerializer : Serializer<GeoLocationStoreModel> {
    override val defaultValue: GeoLocationStoreModel = GeoLocationStoreModel.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): GeoLocationStoreModel =
        kotlin.runCatching {
            GeoLocationStoreModel.parseFrom(input)
        }.getOrElse { throwable: Throwable ->
            Log.e(
                GeoLocationStoreModelSerializer::class.java.simpleName,
                "readFrom: got exception ${throwable.cause?.message ?: throwable.message}"
            )
            GeoLocationStoreModel.getDefaultInstance()
        }

    override suspend fun writeTo(t: GeoLocationStoreModel, output: OutputStream) =
        t.writeTo(output)
}


