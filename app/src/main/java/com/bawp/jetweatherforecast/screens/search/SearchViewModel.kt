package com.bawp.jetweatherforecast.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bawp.jetweatherforecast.data.DataOrException
import com.bawp.jetweatherforecast.domain.usecase.datastore.DataStoreUseCase
import com.bawp.jetweatherforecast.domain.usecase.search.SearchUseCase
import com.bawp.jetweatherforecast.domain.model.locations.GeoLocationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private val _geolocations: MutableStateFlow<DataOrException<List<GeoLocationUiModel>, Boolean, Exception>> =
        MutableStateFlow(value = DataOrException())
    val geolocations: StateFlow<DataOrException<List<GeoLocationUiModel>, Boolean, Exception>> =
        _geolocations

    suspend fun getGeoLocations(query: String) {
        _geolocations.value = searchUseCase.getGeoLocations(query)
    }

    fun storeSelectedLocation(geoLocationUiModel: GeoLocationUiModel) = viewModelScope.launch {
        dataStoreUseCase.storeSelectedLocation(geoLocationUiModel)
    }
}