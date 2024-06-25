package com.example.parkingnext.ui

import android.icu.util.Calendar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.model.ParkingTime
import com.example.parkingnext.ui.theme.ParkingNextTheme

@Composable
fun ReserveTime(
    backButtonOnClick: () -> Unit,
    nextButtonOnClick: () -> Unit,
    modifier: Modifier = Modifier
        .safeDrawingPadding()
        .padding(
            top = 20.dp,
            bottom = 20.dp,
            start = 30.dp,
            end = 30.dp
        )
) {
    Scaffold(
        topBar = { ReserveTimeTopBar(backButtonOnClick) },
        bottomBar = { ParkingSlotsBottomBar(
            goBackOnClick = backButtonOnClick,
            nextOnClick = nextButtonOnClick
        ) },
        modifier = modifier
    ) {
        ReserveTimeBody(Modifier.padding(it))
    }
}

@Composable
fun ReserveTimeTopBar(
    backButtonOnClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = backButtonOnClick,
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.Black,
                )
            }
        }

        Text(
            text = stringResource(id = R.string.space_reserve),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.weight(2f))

        Spacer(Modifier.weight(1f))
    }
}

@Composable
fun ReserveTimeBody(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.reserve_slot),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
        BeginningTimeSelector()
        Text(
            text = stringResource(id = R.string.parking_time),
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.size(5.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.time),
                fontWeight = FontWeight.Thin
            )
        }
        ParkingTimeSelector()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeginningTimeSelector() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.beginning_time),
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )

        Spacer(Modifier.size(10.dp))
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            val currentTime = Calendar.getInstance().apply{ viewModel.getCurrentDate()}
            val state = rememberTimePickerState(
                initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
                initialMinute = currentTime.get(Calendar.MINUTE),
                is24Hour = false
            )

            TimePicker(
                state = state,
                colors = TimePickerDefaults.colors(
                    timeSelectorSelectedContentColor = colorResource(id = R.color.MainOrange),
                    timeSelectorSelectedContainerColor = colorResource(id = R.color.PaleOrange),
                    periodSelectorSelectedContentColor = colorResource(id = R.color.MainOrange),
                    containerColor = colorResource(id = R.color.LightBlue),
                    periodSelectorSelectedContainerColor = colorResource(id = R.color.PaleOrange)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            LaunchedEffect(state.hour) {
                viewModel.selectedDay.set(Calendar.HOUR_OF_DAY, state.hour)
            }
            LaunchedEffect(state.minute) {
                viewModel.selectedDay.set(Calendar.MINUTE, state.minute)
            }
        }
    }
}

@Composable
fun ParkingTimeSelector() {
    val listOfDurations = enumValues<ParkingTime>().toList()

    LazyRow {
        items(listOfDurations) { duration ->
            if (viewModel.selectedDuration == duration)
                SelectedTimeCard(duration) {
                    viewModel.selectedDuration = duration
                }
            else
                UnselectedTimeCard(duration) {
                    viewModel.selectedDuration = duration
                }
        }
    }
}

@Composable
fun UnselectedTimeCard(
    duration: ParkingTime,
    onClick: () -> Unit
) {
    TimeCard(duration = duration,
        cardColor = Color.Transparent,
        onClick = onClick,
        textColor = colorResource(id = R.color.MediumGray),
        border = BorderStroke(2.dp, colorResource(id = R.color.MediumGray))
    )
}

@Composable
fun SelectedTimeCard(
    duration: ParkingTime,
    onClick: () -> Unit
) {
    TimeCard(duration = duration,
        onClick = onClick,
        cardColor = colorResource(id = R.color.MainOrange),
        textColor = Color.White,
        border = BorderStroke(0.dp, Color.Transparent)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeCard(
    duration: ParkingTime,
    cardColor: Color,
    textColor: Color,
    border: BorderStroke,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(cardColor),
        onClick = onClick,
        border = border,
        modifier = Modifier
            .width(65.dp)
            .aspectRatio(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Thin,
                        fontSize = 30.sp
                    )
                    ) {
                        append(duration.time)
                    }
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Thin,
                        fontSize = 12.sp
                    )) {
                        append("\n" + duration.measure)
                    }
                },
                textAlign = TextAlign.Center,
                color = textColor,
                style = LocalTextStyle.current.copy(lineHeight = 10.sp)
            )
        }
    }
    Spacer(modifier = Modifier.size(10.dp))
}

@Preview(showBackground = true)
//@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
//@Preview(device = Devices.PIXEL_3, showBackground = true)
@Composable
fun ReserveTimePreview() {
    ParkingNextTheme {
        ReserveTime({}, {})
    }
}