package com.bawp.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bawp.jetweatherforecast.R
import com.bawp.jetweatherforecast.model.weather.OneCallWeatherUiModel
import com.bawp.jetweatherforecast.utils.formatDate
import com.bawp.jetweatherforecast.utils.formatDateTime
import com.bawp.jetweatherforecast.utils.formatDecimals


@Composable
fun WeatherDetailRow(dailyWeather: OneCallWeatherUiModel.DailyUiModel) {
    with(dailyWeather) {
        Surface(
            Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
            color = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                dt?.let {
                    Text(
                        formatDate(dt)
                            .split(",")[0],
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                weather?.get(0)?.let { weatherToday ->
                    weatherToday.icon?.let { icon ->
                        val imageUrl = "https://openweathermap.org/img/wn/${icon}.png"
                        WeatherStateImage(imageUrl = imageUrl)
                    }

                    weatherToday.description?.let { description ->
                        Surface(
                            modifier = Modifier.padding(0.dp),
                            shape = CircleShape,
                            color = Color(0xFFFFC400)
                        ) {
                            Text(
                                description,
                                modifier = Modifier.padding(4.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                Text(text = buildAnnotatedString {
                    temp?.max?.let {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue.copy(alpha = 0.7f),
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(formatDecimals(temp.max) + "º")

                        }
                    }
                    temp?.min?.let {
                        withStyle(
                            style = SpanStyle(
                                color = Color.LightGray
                            )
                        ) {
                            append(formatDecimals(temp.min) + "º")
                        }
                    }
                })

            }
        }
    }
}

@Composable
fun SunsetSunRiseRow(current: OneCallWeatherUiModel.CurrentUiModel) {
    with(current) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            sunrise?.let {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.sunrise),
                        contentDescription = "sunrise",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = formatDateTime(sunrise),
                        style = MaterialTheme.typography.bodySmall
                    )

                }
            }
            sunset?.let {
                Row {
                    Text(
                        text = formatDateTime(sunset),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sunset),
                        contentDescription = "sunset",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HumidityWindPressureRow(
    current: OneCallWeatherUiModel.CurrentUiModel,
    isImperial: Boolean
) {
    with(current) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            humidity?.let {
                Row(modifier = Modifier.padding(4.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.humidity),
                        contentDescription = "humidity icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "${humidity}%",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            pressure?.let {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.pressure),
                        contentDescription = "pressure icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "$pressure psi",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            windSpeed?.let {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.wind),
                        contentDescription = "wind icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "${formatDecimals(windSpeed)} " + if (isImperial) "mph" else "m/s",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

    }

}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier.size(80.dp)
    )

}
