package com.kashif.weathertestnooro.presentation.components



import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.kashif.weathertestnooro.ui.theme.Dimens.font12
import com.kashif.weathertestnooro.ui.theme.Dimens.font15
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight18
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight22
import com.kashif.weathertestnooro.ui.theme.TextColorLight
import com.kashif.weathertestnooro.ui.theme.TextColorLight2
import com.kashif.weathertestnooro.utils.Poppins

/**
 * Created by Mohammad Kashif Ansari on 21,December,2024
 */

@Composable
fun TextComponent15(modifier: Modifier,data:String,textAlign : TextAlign){
    Text(data,
        modifier=modifier,
        style = TextStyle(fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = font15,
            lineHeight = lineHeight22 ,
            textAlign = textAlign,
            color = TextColorLight2
        )
    )
}

@Composable
fun TextComponent12(modifier: Modifier,data:String,textAlign : TextAlign){
    Text(
       data,
        modifier=modifier,
        style = TextStyle(fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = font12,
            lineHeight = lineHeight18 ,
            textAlign = textAlign,
            color = TextColorLight
        )
    )
}