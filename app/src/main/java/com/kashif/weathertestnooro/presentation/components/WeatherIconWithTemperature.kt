package com.kashif.weathertestnooro.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.kashif.weathertestnooro.R
import com.kashif.weathertestnooro.data.model.WeatherResponse
import com.kashif.weathertestnooro.ui.theme.BoxColor
import com.kashif.weathertestnooro.ui.theme.Dimens.cornerRadius
import com.kashif.weathertestnooro.ui.theme.Dimens.font30
import com.kashif.weathertestnooro.ui.theme.Dimens.font40
import com.kashif.weathertestnooro.ui.theme.Dimens.font70
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight105
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight45
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight50
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight65
import com.kashif.weathertestnooro.ui.theme.Dimens.padding10
import com.kashif.weathertestnooro.ui.theme.Dimens.padding100
import com.kashif.weathertestnooro.ui.theme.Dimens.padding20
import com.kashif.weathertestnooro.ui.theme.Dimens.padding21
import com.kashif.weathertestnooro.ui.theme.Dimens.padding30
import com.kashif.weathertestnooro.ui.theme.Dimens.padding5
import com.kashif.weathertestnooro.ui.theme.Dimens.size10
import com.kashif.weathertestnooro.ui.theme.Dimens.size123
import com.kashif.weathertestnooro.ui.theme.Dimens.size21
import com.kashif.weathertestnooro.ui.theme.Dimens.size4
import com.kashif.weathertestnooro.ui.theme.Dimens.size5
import com.kashif.weathertestnooro.ui.theme.Dimens.size8
import com.kashif.weathertestnooro.ui.theme.TextColorDark
import com.kashif.weathertestnooro.ui.theme.TextColorLight
import com.kashif.weathertestnooro.utils.Constants.getAirQuality
import com.kashif.weathertestnooro.utils.Constants.getFilteredWeatherData
import com.kashif.weathertestnooro.utils.Poppins
import com.kashif.weathertestnooro.utils.Resources

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherIconWithTemperature(weatherState: Resources<WeatherResponse>?) {
    if(weatherState?.data!=null){
        val weatherData = weatherState.data
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = padding21), horizontalAlignment = Alignment.CenterHorizontally) {
            // Weather Icon (Sun and Clouds)
            Box(contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = "https:"+weatherData.current.condition.icon, // URL from the API response
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(size123)
                )
            }

            // Location and Temperature
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = weatherData.location.name,
                    Modifier.testTag("name"),
                    style = TextStyle(fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = font30,
                        lineHeight = lineHeight45 ,
                        color = TextColorDark),
                )
                Spacer(modifier = Modifier.width(size10))
                Icon(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(size21)
                )
            }

            Spacer(modifier = Modifier.height(size8))
            //temperature block
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = weatherData.current.temp_c.toString(),
                    Modifier.testTag("temp"),
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = font70,
                        lineHeight = lineHeight105,
                        textAlign = TextAlign.Center,
                        color = TextColorDark
                    )
                )
                Icon(
                    painter = painterResource(R.drawable.degree_symbol),
                    contentDescription = "degree",
                    tint = TextColorDark,
                    modifier = Modifier
                        .size(size8)
                )
            }
//           humidity box
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding30, start = padding30, end = padding30), contentAlignment = Alignment.Center){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(cornerRadius))
                        .background(BoxColor)
                        .padding(vertical = padding20),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextComponent12(modifier = Modifier,stringResource(R.string.humidity), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(size4))
                        TextComponent15(modifier = Modifier.testTag("humidity"),weatherData.current.humidity.toString()+ stringResource(R.string.percentage_symbol), textAlign = TextAlign.Center)

                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextComponent12( modifier = Modifier,stringResource(R.string.uv), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(size4))
                        TextComponent15(modifier = Modifier.testTag("uv"),weatherData.current.uv.toString(), textAlign = TextAlign.Center)

                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextComponent12( modifier = Modifier,stringResource(R.string.feels_like), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(size4))
                        TextComponent15(modifier = Modifier.testTag("feels"),weatherData.current.feelslike_c.toString()+ stringResource(R.string.degree_symbol), textAlign = TextAlign.Center)

                    }
                }
            }
            //forecast of 7 days
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding30, end = padding30, top = padding10)
                .clip(RoundedCornerShape(cornerRadius))
                .background(BoxColor),
                contentAlignment = Alignment.Center)
            {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = padding10, bottom = padding10), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = padding20), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_access_time_24),
                            contentDescription = "degree",
                            tint = TextColorDark,
                            modifier = Modifier
                                .size(size8)
                        )
                        Spacer(modifier = Modifier.width(size4))
                        TextComponent12( modifier = Modifier,stringResource(R.string.hourly_forecast), textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.height(size4))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        val forecast = weatherData.forecast?.forecastday
                            val forecastDay= forecast?.let { getFilteredWeatherData(it) }
                            forecastDay?.let { it1 ->
                                items(it1.size) { index ->
                                    val weather = forecastDay[index]
                                    ForecastWeakList(
                                        time = if(index==0) stringResource(R.string.now) else weather.time.substringAfter(" "), // Extract hour (e.g., "10:00")
                                        icon = weather.condition.icon,
                                        temperature = "${weather.temp_c}°"
                                    )
                                }
                            }
                    }
                }

            }
            //wind
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding30, end = padding30, top = padding10)
                .clip(RoundedCornerShape(cornerRadius))
                .background(BoxColor),
                contentAlignment = Alignment.Center) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = padding20, top = padding10),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.wind),
                                contentDescription = "degree",
                                tint = TextColorDark,
                                modifier = Modifier
                                    .size(size8)
                            )
                            Spacer(modifier = Modifier.width(size4))
                            TextComponent12( modifier = Modifier,stringResource(R.string.wind), textAlign = TextAlign.Center)

                        }
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = padding20,
                                end = padding20,
                                top = padding10,
                                bottom = padding10
                            )) {
                            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                                // Create references for the Text components
                                val (startText, endText) = createRefs()

                                // Start-aligned text
                                TextComponent15(
                                    data = stringResource(R.string.wind),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.constrainAs(startText) {
                                        start.linkTo(parent.start) // Align to start of the parent
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                                )

                                // End-aligned text
                                TextComponent15(
                                    data = weatherData.current.wind_kph.toString() + stringResource(R.string.kph),
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.constrainAs(endText) {
                                        end.linkTo(parent.end) // Align to end of the parent
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                                )
                            }
                            Divider(modifier = Modifier.padding(vertical = size5)) // Add a divider
                            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                                // Create references for the Text components
                                val (startText, endText) = createRefs()

                                // Start-aligned text
                                TextComponent15(
                                    data = stringResource(R.string.gusts),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.constrainAs(startText) {
                                        start.linkTo(parent.start) // Align to start of the parent
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                                )

                                // End-aligned text
                                TextComponent15(
                                    data = weatherData.current.gust_kph.toString() + stringResource(R.string.kph),
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.constrainAs(endText) {
                                        end.linkTo(parent.end) // Align to end of the parent
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                                )
                            }

                            Divider(modifier = Modifier.padding(vertical = size5)) // Add a divider
                            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                                // Create references for the Text components
                                val (startText, endText) = createRefs()

                                // Start-aligned text
                                TextComponent15(
                                    data = stringResource(R.string.direction),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.constrainAs(startText) {
                                        start.linkTo(parent.start) // Align to start of the parent
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                                )

                                // End-aligned text
                                TextComponent15(
                                    data = weatherData.current.wind_degree.toString() + stringResource(R.string.degree_symbol) + weatherData.current.wind_dir,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.constrainAs(endText) {
                                        end.linkTo(parent.end) // Align to end of the parent
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                                )
                            }

                        }

                    }
                }
            //air quality
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding30, end = padding30, top = padding10)
                .clip(RoundedCornerShape(cornerRadius))
                .background(BoxColor),
                contentAlignment = Alignment.Center) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = padding20, top = padding10),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.wind),
                            contentDescription = "degree",
                            tint = TextColorDark,
                            modifier = Modifier
                                .size(size8)
                        )
                        Spacer(modifier = Modifier.width(size4))
                        TextComponent12(modifier = Modifier,stringResource(R.string.air_quality), textAlign = TextAlign.Center)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = padding20,
                            end = padding20,
                            top = padding10,
                            bottom = padding10
                        )) {

                        Text(
                            weatherData.current.air_quality.pm10.toInt().toString(),
                            style = TextStyle(
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = font40,
                                lineHeight = lineHeight50,
                                textAlign = TextAlign.Start, // Align to right
                                color = TextColorLight
                            )
                        )
                        Text(getAirQuality(weatherData.current.air_quality.us_epa_index),
                            style = TextStyle(
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = font40,
                                lineHeight = lineHeight50,
                                textAlign = TextAlign.Start, // Align to left
                                color = TextColorLight
                            )
                        )
                        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                            // Create references for the Text components
                            val (startText, endText) = createRefs()

                            // Start-aligned text
                            TextComponent15(
                                data = stringResource(R.string.co_carbon_monoxide),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.constrainAs(startText) {
                                    start.linkTo(parent.start) // Align to start of the parent
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                            )

                            // End-aligned text
                            TextComponent15(
                                data = weatherData.current.air_quality.co.toString() + stringResource(
                                    R.string.g_m3
                                ),
                                textAlign = TextAlign.End,
                                modifier = Modifier.constrainAs(endText) {
                                    end.linkTo(parent.end) // Align to end of the parent
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                            )
                        }

                        Divider(modifier = Modifier.padding(vertical = padding5)) // Add a divider
                        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                            // Create references for the Text components
                            val (startText, endText) = createRefs()

                            // Start-aligned text
                            TextComponent15(
                                data = stringResource(R.string.no2_nitrogen_dioxide),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.constrainAs(startText) {
                                    start.linkTo(parent.start) // Align to start of the parent
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                            )

                            // End-aligned text
                            TextComponent15(
                                data = weatherData.current.air_quality.no2.toString() + stringResource(
                                    R.string.g_m3
                                ),
                                textAlign = TextAlign.End,
                                modifier = Modifier.constrainAs(endText) {
                                    end.linkTo(parent.end) // Align to end of the parent
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                            )
                        }

                        Divider(modifier = Modifier.padding(vertical = padding5)) // Add a divider
                        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                            // Create references for the Text components
                            val (startText, endText) = createRefs()

                            // Start-aligned text
                            TextComponent15(
                                data = stringResource(R.string.o3_ozone),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.constrainAs(startText) {
                                    start.linkTo(parent.start) // Align to start of the parent
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                            )

                            // End-aligned text
                            TextComponent15(
                                data = weatherData.current.air_quality.o3.toString() + stringResource(
                                    R.string.g_m3
                                ),
                                textAlign = TextAlign.End,
                                modifier = Modifier.constrainAs(endText) {
                                    end.linkTo(parent.end) // Align to end of the parent
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                            )
                        }

                    }

                }
            }

            //box of precipitation and visibility
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding10, start = padding30, end = padding30), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
            {
                Box(modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(BoxColor),
                    contentAlignment = Alignment.Center) {
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = padding20, top = padding10),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.precipitation),
                                contentDescription = "degree",
                                tint = TextColorDark,
                                modifier = Modifier
                                    .size(size8)
                            )
                            Spacer(modifier = Modifier.width(size4))
                            TextComponent12(modifier = Modifier,stringResource(R.string.precipitation), textAlign = TextAlign.Center)

                        }
                        Spacer(modifier = Modifier.height(size10))
                        Text(
                            weatherData.current.precip_mm.toString() + stringResource(R.string.mm),
                            style = TextStyle(
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = font30,
                                lineHeight = lineHeight65,
                                textAlign = TextAlign.Center,
                                color = TextColorLight
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(size10))
                Box(modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(BoxColor),
                    contentAlignment = Alignment.Center) {
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = padding20, top = padding10),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.visibility),
                                contentDescription = "degree",
                                tint = TextColorDark,
                                modifier = Modifier
                                    .size(size8)
                            )
                            Spacer(modifier = Modifier.width(size4))
                            TextComponent12( modifier = Modifier,stringResource(R.string.visibility), textAlign = TextAlign.Center)
                        }
                        Spacer(modifier = Modifier.height(size10))
                        Text(
                            weatherData.current.vis_km.toString() + stringResource(R.string.km),
                            style = TextStyle(
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = font30,
                                lineHeight = lineHeight65,
                                textAlign = TextAlign.Center,
                                color = TextColorLight
                            )
                        )
                    }
                }
            }
            //box of pressure and humidity
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding10, start = padding30, end = padding30, bottom = padding100), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
            {
                Box(modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(BoxColor), contentAlignment = Alignment.Center) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = padding20, top = padding10),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.pressure),
                                contentDescription = "degree",
                                tint = TextColorDark,
                                modifier = Modifier
                                    .size(size8)
                            )
                            Spacer(modifier = Modifier.width(size4))
                            TextComponent12(modifier = Modifier,stringResource(R.string.pressure), textAlign = TextAlign.Center)

                        }
                        Spacer(modifier = Modifier.height(padding10))
                        Text(
                            weatherData.current.pressure_mb.toString() + stringResource(R.string.hpa),
                            modifier = Modifier.padding(start = padding20),
                            style = TextStyle(
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 30.sp,
                                lineHeight = 65.sp,
                                textAlign = TextAlign.Center,
                                color = TextColorLight
                            )
                        )
                    }
                }

            }

        }

    }

}
