package com.kashif.weathertestnooro

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import com.kashif.weathertestnooro.data.model.AirQuality
import com.kashif.weathertestnooro.data.model.Condition
import com.kashif.weathertestnooro.data.model.Current
import com.kashif.weathertestnooro.data.model.Location
import com.kashif.weathertestnooro.data.model.WeatherResponse
import com.kashif.weathertestnooro.presentation.components.WeatherIconWithTemperature
import com.kashif.weathertestnooro.utils.Resources
import org.junit.Rule
import org.junit.Test

/**
 * Created by Mohammad Kashif Ansari on 17,December,2024
 */
class weatherIconWithTemperatureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWeatherIcon(){
        composeTestRule.setContent {
            val mockResponse = WeatherResponse(
                location = Location(
                    name = "Kanpur",
                ),
                current = Current(
                    temp_c = 25.0,
                    condition = Condition(text="Clear", icon = ""),
                    humidity = 50,
                    uv = 5.0,
                    feelslike_c = 26.0,
                    gust_kph = 5.9,
                    precip_mm = 0.0,
                    pressure_mb = 0.0,
                    vis_km = 0.0,
                    wind_degree = 0,
                    wind_dir = "",
                    wind_kph = 0.0,
                    windchill_c = 0.0,
                    air_quality = AirQuality(co=0f,no2=0f, so2 = 0f,pm2_5=0f,pm10=0f, us_epa_index = 0, gb_defra_index = 0,o3=0f)
            )
                ,  forecast = null//Forecast()
            )
            val fakeSuccessState = Resources.Success(mockResponse)
            WeatherIconWithTemperature(fakeSuccessState)
        }

        // Verify that the locationName text is shown
        composeTestRule.onNodeWithTag("name").assertIsDisplayed()

        // Verify that the temperature text is shown
        composeTestRule.onNodeWithTag("temp").assertIsDisplayed()

        // Verify that the humidity text is shown
        composeTestRule.onNodeWithTag("humidity").assertIsDisplayed()

        // Verify that the uv text is shown
        composeTestRule.onNodeWithTag("uv").assertIsDisplayed()

        // Verify that the feelsLike text is shown
        composeTestRule.onNodeWithTag("feels").assertIsDisplayed()



        // Verify the AsyncImage is displayed with the correct URL
        composeTestRule.onNodeWithContentDescription("Weather Icon")
            .assertExists()
    }
}