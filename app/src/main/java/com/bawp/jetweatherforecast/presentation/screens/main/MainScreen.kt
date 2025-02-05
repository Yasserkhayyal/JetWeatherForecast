package com.bawp.jetweatherforecast.presentation.screens.main

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bawp.jetweatherforecast.domain.model.locations.GeoLocationUiModel
import com.bawp.jetweatherforecast.domain.model.locations.isNotEmpty
import com.bawp.jetweatherforecast.domain.model.weather.OneCallWeatherUiModel
import com.bawp.jetweatherforecast.navigation.WeatherScreens
import com.bawp.jetweatherforecast.presentation.screens.settings.SettingsViewModel
import com.bawp.jetweatherforecast.presentation.utils.formatDate
import com.bawp.jetweatherforecast.presentation.utils.formatDecimals
import com.bawp.jetweatherforecast.presentation.widgets.HumidityWindPressureRow
import com.bawp.jetweatherforecast.presentation.widgets.SunsetSunRiseRow
import com.bawp.jetweatherforecast.presentation.widgets.WeatherAppBar
import com.bawp.jetweatherforecast.presentation.widgets.WeatherDetailRow
import com.bawp.jetweatherforecast.presentation.widgets.WeatherStateImage

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
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
    val isImperial by remember(units) {
        mutableStateOf(units == "imperial")
    }

    val selectedGeoLocation by mainViewModel.selectedLocationFlow.collectAsState(
        initial = null
    )

    val weatherData by mainViewModel.weatherData.collectAsState()
    val activity = LocalActivity.current

    BackHandler {
        activity?.finish()
    }

    LaunchedEffect(selectedGeoLocation) {
        selectedGeoLocation?.run {
            if (isNotEmpty()) {
                mainViewModel.getWeatherData(
                    lat = lat,
                    lon = lon,
                    units = units
                )
            }
        }
    }

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else {
        MainScaffold(
            selectedGeoLocation = selectedGeoLocation,
            weather = weatherData.data,
            navController = navController,
            isImperial = isImperial,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    selectedGeoLocation: GeoLocationUiModel?,
    weather: OneCallWeatherUiModel?,
    navController: NavController,
    isImperial: Boolean
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            WeatherAppBar(
                title = if (!selectedGeoLocation?.locationQuery.isNullOrEmpty()) {
                    selectedGeoLocation?.locationQuery ?: "Weather"
                } else {
                    "Weather"
                },
                isMainScreen = true,
                currentLocation = selectedGeoLocation,
                scrollBehavior = topAppBarScrollBehavior,
                navController = navController,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                }
            ) {
                Log.d("TAG", "MainScaffold: Button Clicked")
            }
        }) { contentPadding ->
        weather?.let {
            MainContent(
                modifier = Modifier.padding(contentPadding),
                data = weather,
                isImperial = isImperial
            )
        }
    }
}

@Composable
fun MainContent(modifier: Modifier, data: OneCallWeatherUiModel, isImperial: Boolean) {
    Column(
        modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        data.current?.run {
            dt?.let {
                Text(
                    text = formatDate(dt), // Wed Nov 30
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(6.dp)
                )
            }

            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .size(200.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                val todayWeather = weather?.get(0)
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    todayWeather?.icon?.let { icon ->
                        val imageUrl = "https://openweathermap.org/img/wn/${icon}.png"
                        WeatherStateImage(imageUrl = imageUrl)
                    }
                    temp?.let {
                        Text(
                            text = formatDecimals(temp) + "º",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    todayWeather?.main?.let {
                        Text(
                            text = it,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
            HumidityWindPressureRow(current = this, isImperial = isImperial)
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            SunsetSunRiseRow(current = this)
        }
        if (!data.daily.isNullOrEmpty()) {
            Text(
                "This Week",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                color = Color(0xFFEEF1EF),
                shape = RoundedCornerShape(size = 14.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(2.dp),
                    contentPadding = PaddingValues(1.dp)
                ) {
                    items(items = data.daily) { item: OneCallWeatherUiModel.DailyUiModel ->
                        WeatherDetailRow(dailyWeather = item)
                    }
                }
            }
        }
    }
}
