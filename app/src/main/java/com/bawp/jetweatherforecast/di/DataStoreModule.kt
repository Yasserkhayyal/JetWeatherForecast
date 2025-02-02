package com.bawp.jetweatherforecast.di

import com.bawp.jetweatherforecast.domain.DataStoreRepository
import com.bawp.jetweatherforecast.domain.DataStoreUseCase
import com.bawp.jetweatherforecast.domain.DataStoreUseCaseImp
import com.bawp.jetweatherforecast.repository.DataStoreRepositoryImp
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