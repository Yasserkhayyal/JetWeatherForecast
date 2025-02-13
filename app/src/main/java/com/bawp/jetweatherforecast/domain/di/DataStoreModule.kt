package com.bawp.jetweatherforecast.domain.di

import com.bawp.jetweatherforecast.domain.repository.DataStoreRepository
import com.bawp.jetweatherforecast.domain.usecase.datastore.DataStoreUseCase
import com.bawp.jetweatherforecast.domain.usecase.datastore.DataStoreUseCaseImp
import com.bawp.jetweatherforecast.data.repository.datastore.DataStoreRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataStoreModule {
    @Binds
    fun bindsDataStoreUseCase(dataStoreUseCaseImp: DataStoreUseCaseImp): DataStoreUseCase

    @Binds
    fun bindsDataStoreRepository(dataStoreRepositoryImp: DataStoreRepositoryImp): DataStoreRepository
}