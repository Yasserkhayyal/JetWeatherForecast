package com.bawp.jetweatherforecast.screens.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bawp.jetweatherforecast.model.locations.GeoLocationUiModel
import com.bawp.jetweatherforecast.navigation.WeatherScreens
import com.bawp.jetweatherforecast.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {
    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            WeatherAppBar(
                title = "Search",
                scrollBehavior = topAppBarScrollBehavior,
                navController = navController,
            ) {
                navController.popBackStack()
            }
        }) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    viewModel = viewModel
                ) {
                    navController.navigate(WeatherScreens.MainScreen.name)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    onSearchItemSelected: () -> Unit
) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val expanded = remember { mutableStateOf(false) }
    val suggestedLocations by viewModel.geolocations.collectAsState()

    LaunchedEffect(searchQueryState.value) {
        if (searchQueryState.value.isNotEmpty()) {
            viewModel.getGeoLocations(query = searchQueryState.value)
        }
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded.value,
        onExpandedChange = { },
    ) {
        CommonTextField(
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
            valueState = searchQueryState,
            showSearchResults = expanded,
            supportingText = {
                Text(
                    text = suggestedLocations.e?.message ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error
                )
            },
            placeholder = "Enter your city name, state code or country code"
        )

        when {
            suggestedLocations.loading == true -> CircularProgressIndicator()
            suggestedLocations.e != null -> {/* no-op , already handled in CommonTextField */
            }

            !suggestedLocations.data.isNullOrEmpty() -> {
                ExposedDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    suggestedLocations.data?.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    modifier = Modifier.clickable {
                                        onSearchItemSelected(
                                            viewModel,
                                            item,
                                            searchQueryState,
                                            keyboardController,
                                            expanded,
                                            onSearchItemSelected
                                        )
                                    },
                                    text = item.locationQuery,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            },
                            onClick = {
                                searchQueryState.value = item.locationQuery
                                keyboardController?.hide()
                                expanded.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun onSearchItemSelected(
    viewModel: SearchViewModel,
    selectedItem: GeoLocationUiModel,
    searchQueryState: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    expanded: MutableState<Boolean>,
    onSearchItemSelected: () -> Unit
) {
    viewModel.storeSelectedLocation(selectedItem).invokeOnCompletion { cause ->
        when (cause) {
            null -> onSearchItemSelected()
            else -> {
                Log.e("SearchScreen", "an error happened: $cause")
            }
        }
    }
    searchQueryState.value = selectedItem.locationQuery
    keyboardController?.hide()
    expanded.value = false
}

@Composable
fun CommonTextField(
    modifier: Modifier,
    valueState: MutableState<String>,
    showSearchResults: MutableState<Boolean>,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    supportingText: @Composable (() -> Unit)? = null,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    val interactionSource = remember { MutableInteractionSource() }
        .also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        showSearchResults.value = true
                    }
                }
            }
        }

    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            showSearchResults.value = true
        },
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        supportingText = supportingText,
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )
}











