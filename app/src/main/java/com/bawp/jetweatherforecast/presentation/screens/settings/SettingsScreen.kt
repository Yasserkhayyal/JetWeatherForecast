@file:OptIn(ExperimentalMaterial3Api::class)

package com.bawp.jetweatherforecast.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bawp.jetweatherforecast.presentation.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    val unitsFromDb by settingsViewModel.unitList.collectAsState()
    val units by remember(unitsFromDb) {
        mutableStateOf(
            if (unitsFromDb.isNotEmpty()) {
                unitsFromDb[0].unit.split(" ")[0].lowercase()
            } else {
                "imperial"
            }
        )
    }
    val isImperialChipSelected = remember(units) {
        mutableStateOf(units == "imperial")
    }

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            WeatherAppBar(
                title = "Settings",
                scrollBehavior = topAppBarScrollBehavior,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(contentPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "Change Units of Measurement",
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    FilterChip(
                        selected = isImperialChipSelected.value,
                        onClick = {
                            isImperialChipSelected.value = !isImperialChipSelected.value
                        },
                        label = { Text(text = "Fahrenheit ºF") },
                        leadingIcon = if (isImperialChipSelected.value) {
                            { Icon(imageVector = Icons.Filled.Check, contentDescription = "") }
                        } else {
                            null
                        }
                    )
                    FilterChip(
                        selected = !isImperialChipSelected.value,
                        onClick = { isImperialChipSelected.value = !isImperialChipSelected.value },
                        label = { Text("Celsius ºC") },
                        leadingIcon = if (!isImperialChipSelected.value) {
                            { Icon(imageVector = Icons.Filled.Check, contentDescription = "") }
                        } else {
                            null
                        }
                    )
                }

                Button(
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnit(
                            isImperialUnitSelected = isImperialChipSelected.value
                        )
                    },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                ) {
                    Text(
                        text = "Save",
                        modifier = Modifier.padding(4.dp),
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}