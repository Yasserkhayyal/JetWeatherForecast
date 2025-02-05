package com.bawp.jetweatherforecast.presentation.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bawp.jetweatherforecast.data.model.favorite.Unit
import com.bawp.jetweatherforecast.data.repository.weather.WeatherDbRepository
import com.bawp.jetweatherforecast.presentation.utils.Constants.IMPERIAL
import com.bawp.jetweatherforecast.presentation.utils.Constants.METRIC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
) : ViewModel() {
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged()
                .collect { listOfUnits ->
                    Log.d("SettingsViewModel", "listOfUnits: $listOfUnits")
                    _unitList.value = listOfUnits
                }
        }
    }

    fun insertUnit(isImperialUnitSelected: Boolean) = viewModelScope.launch {
        val unit = if (isImperialUnitSelected) Unit(IMPERIAL) else Unit(METRIC)
        repository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch { repository.updateUnit(unit) }
    fun deleteUnit(unit: Unit) = viewModelScope.launch { repository.deleteUnit(unit) }
    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }
}