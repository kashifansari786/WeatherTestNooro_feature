package com.kashif.weathertestnooro.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kashif.weathertestnooro.R
import com.kashif.weathertestnooro.data.model.WeatherResponse
import com.kashif.weathertestnooro.presentation.HomeViewModel
import com.kashif.weathertestnooro.room.SearchedCity
import com.kashif.weathertestnooro.ui.theme.Dimens.cornerRadius
import com.kashif.weathertestnooro.ui.theme.Dimens.font20
import com.kashif.weathertestnooro.ui.theme.Dimens.lineHeight30
import com.kashif.weathertestnooro.ui.theme.Dimens.padding10
import com.kashif.weathertestnooro.ui.theme.Dimens.padding20
import com.kashif.weathertestnooro.ui.theme.Dimens.padding25
import com.kashif.weathertestnooro.ui.theme.Dimens.size5
import com.kashif.weathertestnooro.ui.theme.Dimens.size50
import com.kashif.weathertestnooro.utils.Poppins
import com.kashif.weathertestnooro.utils.Resources
import kotlinx.coroutines.delay

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T>SwipeToDeleteContainer(
    item:T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state =  rememberDismissState(
        confirmStateChange = {value->
            if(value == DismissValue.DismissedToStart){
                isRemoved = true
                true
            }else{
                false
            }
        }
    )
    LaunchedEffect(key1=isRemoved){
        if(isRemoved){
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }
    AnimatedVisibility(visible = !isRemoved, exit = shrinkVertically(animationSpec = tween(durationMillis = animationDuration), shrinkTowards = Alignment.Top) + fadeOut()){
        SwipeToDismiss(state = state,
            background = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = padding25, end = padding25, top = padding10)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(cornerRadius))
                            .background(Color.Red)
                            .padding(vertical = padding10),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                top = padding10,
                                end = padding20,
                                bottom = padding10
                            ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = stringResource(R.string.delete),
                                style = TextStyle(
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = font20,
                                    lineHeight = lineHeight30,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.height(size5))

                            Icon(
                                painter = painterResource(R.drawable.baseline_delete_forever_24),
                                contentDescription = "degree",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(size50)
                            )
                        }
                    }
                }
            },
            dismissContent = {
              content(item)
            },
            directions = setOf(DismissDirection.EndToStart)
        )
    }

}


