package com.bawp.jetweatherforecast.data.di

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.bawp.jetweatherforecast.GeoLocationStoreModel
import com.bawp.jetweatherforecast.data.WeatherDao
import com.bawp.jetweatherforecast.data.WeatherDatabase
import com.bawp.jetweatherforecast.domain.GeoLocationStoreModelSerializer
import com.bawp.jetweatherforecast.network.WeatherApi
import com.bawp.jetweatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeoLocationDataStore(@ApplicationContext applicationContext: Context): DataStore<GeoLocationStoreModel> =
        DataStoreFactory.create(
            serializer = GeoLocationStoreModelSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler {
                Log.d(
                    DataStoreFactory::class.java.simpleName,
                    "selected geo location preferences file corrupted"
                )
                GeoLocationStoreModel.getDefaultInstance()
            },
            produceFile = { applicationContext.dataStoreFile("selected_geo_location.pb") }
        )
}