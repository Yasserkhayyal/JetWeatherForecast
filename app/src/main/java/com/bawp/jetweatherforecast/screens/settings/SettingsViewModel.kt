package com.bawp.jetweatherforecast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bawp.jetweatherforecast.model.Unit
import com.bawp.jetweatherforecast.data.repository.WeatherDbRepository
import com.bawp.jetweatherforecast.utils.Constants.IMPERIAL
import com.bawp.jetweatherforecast.utils.Constants.METRIC
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

    fun insertUnit(isToggleButtonChecked: Boolean) = viewModelScope.launch {
        val unit = if (isToggleButtonChecked) Unit(IMPERIAL) else Unit(METRIC)
        repository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch { repository.updateUnit(unit) }
    fun deleteUnit(unit: Unit) = viewModelScope.launch { repository.deleteUnit(unit) }
    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }
}