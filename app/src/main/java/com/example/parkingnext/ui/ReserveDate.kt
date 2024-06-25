package com.example.parkingnext.ui

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.ui.theme.ParkingNextTheme

@Composable
fun ReserveDate(
    backButtonOnClick: () -> Unit,
    nextButtonOnClick: () -> Unit,
    modifier: Modifier = Modifier
        .safeDrawingPadding()
        .padding(
            top = 20.dp,
            bottom = 20.dp
        )
) {
    Scaffold(
        topBar = { ReserveDateTopBar(backButtonOnClick) },
        bottomBar = { ParkingSlotsBottomBar(
            nextOnClick = nextButtonOnClick.apply {  },
            goBackOnClick = backButtonOnClick,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        )},
        modifier = modifier
    ) {
        ReserveDatePicker(Modifier.padding(it))
    }
}

@Composable
fun ReserveDateTopBar(
    backButtonOnClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReserveDatePicker(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.reserve_slot),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        )
        Text(
            text = stringResource(id = R.string.beginning_day),
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        )

        Spacer(Modifier.size(10.dp))

        Surface(
            color = colorResource(id = R.color.LightBlue),
            shape = RoundedCornerShape(5),
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.selectedDay = viewModel.getCurrentDate()
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = viewModel.getCurrentDate().timeInMillis
            )
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    todayDateBorderColor = colorResource(R.color.MainOrange),
                    selectedDayContainerColor = colorResource(R.color.MainOrange)
                ),
                dateValidator = { pickerDateMillis ->
                    val currentDay = viewModel.getCurrentDate()
                    val pickerDay = Calendar.getInstance().apply {timeInMillis = pickerDateMillis}
                    pickerDay.get(Calendar.YEAR) >= currentDay.get(Calendar.YEAR) &&
                            pickerDay.get(Calendar.DAY_OF_YEAR) >=currentDay.get(Calendar.DAY_OF_YEAR)
                }
            )

            LaunchedEffect(datePickerState.selectedDateMillis) {
                datePickerState.selectedDateMillis?.let { selectedMillis ->
                    viewModel.selectedDay.setTimeInMillis(selectedMillis)
                }
            }
        }
    }
}

@Preview(showBackground = true)
//@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
@Composable
fun ReserveDatePreview() {
    ParkingNextTheme {
        ReserveDate({}, {})
    }
}