package com.kashif.weathertestnooro.presentation.components

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kashif.weathertestnooro.R
import com.kashif.weathertestnooro.ui.theme.Black
import com.kashif.weathertestnooro.ui.theme.Dimens.font12
import com.kashif.weathertestnooro.ui.theme.Dimens.font15
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight18
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight22
import com.kashif.weathertestnooro.ui.theme.Dimens.padding25
import com.kashif.weathertestnooro.ui.theme.Dimens.padding40
import com.kashif.weathertestnooro.ui.theme.Dimens.padding80
import com.kashif.weathertestnooro.ui.theme.Dimens.size8
import com.kashif.weathertestnooro.ui.theme.TextColorDark
import com.kashif.weathertestnooro.ui.theme.TextColorLight
import com.kashif.weathertestnooro.ui.theme.TextColorLight2
import com.kashif.weathertestnooro.utils.Poppins

@Composable
fun ForecastWeakList(time: String, icon: String, temperature: String) {
    Column(
        modifier = Modifier.width(padding80),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(size8)
    ) {
        // Time Text
        TextComponent12( modifier = Modifier.fillMaxWidth(),
            time, textAlign = TextAlign.Center)

        // Weather Icon Placeholder

            AsyncImage(
                model = "https:"+icon, // URL from the API response
                contentDescription = "Weather Icon",
                modifier = Modifier.size(padding40)
            )

        // Temperature Text
        TextComponent15( modifier = Modifier.fillMaxWidth(),temperature, textAlign = TextAlign.Center)
    }
}
