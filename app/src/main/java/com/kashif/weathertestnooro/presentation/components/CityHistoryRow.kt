package com.kashif.weathertestnooro.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kashif.weathertestnooro.R
import com.kashif.weathertestnooro.room.SearchedCity
import com.kashif.weathertestnooro.ui.theme.Black
import com.kashif.weathertestnooro.ui.theme.BoxColor
import com.kashif.weathertestnooro.ui.theme.Dimens.cornerRadius
import com.kashif.weathertestnooro.ui.theme.Dimens.font20
import com.kashif.weathertestnooro.ui.theme.Dimens.font60
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight30
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight90
import com.kashif.weathertestnooro.ui.theme.Dimens.padding10
import com.kashif.weathertestnooro.ui.theme.Dimens.padding15
import com.kashif.weathertestnooro.ui.theme.Dimens.padding25
import com.kashif.weathertestnooro.ui.theme.Dimens.size123
import com.kashif.weathertestnooro.ui.theme.Dimens.size5
import com.kashif.weathertestnooro.ui.theme.TextColorDark
import com.kashif.weathertestnooro.utils.Poppins

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
@Composable
fun CityHistoryRow(city: SearchedCity, onCLick:(String) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().padding(start = padding25, end = padding25, top = padding10)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(cornerRadius))
                .background(BoxColor)
                .padding(vertical = padding10)
                .clickable{onCLick(city.cityName)}
            , horizontalArrangement = Arrangement.SpaceAround
            , verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.padding(start =padding15, top = padding10)) {
                Text(
                    text = city.cityName,
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = font20,
                        lineHeight = lineHeight30,
                        color = TextColorDark
                    )
                )
                Spacer(modifier = Modifier.height(size5))
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "${city.cityTemp}",
                        style = TextStyle(
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            fontSize = font60,
                            lineHeight = lineHeight90,
                            color = TextColorDark
                        )
                    )
                    Icon(
                        painter = painterResource(R.drawable.degree_symbol),
                        contentDescription = "degree",
                        tint = Black,
                        modifier = Modifier
                            .size(size5)
                    )
                }

            }
            AsyncImage(
                model = "https:"+city.weatherIcon, // URL from the API response
                contentDescription = "Weather Icon",
                modifier = Modifier.size(size123)
            )
        }
    }
}