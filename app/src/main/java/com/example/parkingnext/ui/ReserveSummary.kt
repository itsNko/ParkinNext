package com.example.parkingnext.ui

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.ui.theme.ParkingNextTheme
import androidx.compose.foundation.layout.size
import com.example.parkingnext.model.Slot
import com.example.parkingnext.model.SpecialSlot
import com.example.parkingnext.model.StandardSlot

@Composable
fun ReserveSummary(
    backButtonOnClick: () -> Unit,
    cancelOnClick: () -> Unit,
    reserveOnClick: () -> Unit,
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
        bottomBar = { ReserveSummaryBottomBar(
            cancelOnClick = cancelOnClick,
            reserveOnClick = reserveOnClick
        ) },
        modifier = modifier
    ) {
        ReserveSummaryBody(Modifier.padding(it))
    }
}

@Composable
fun ReserveSummaryBottomBar(
    cancelOnClick: () -> Unit,
    reserveOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = cancelOnClick,
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                fontWeight = FontWeight.Normal,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                color = Color.Red,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Button(
            onClick = reserveOnClick,
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkerOrange)),
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.reserve_slot),
                fontWeight = FontWeight.SemiBold,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                color = colorResource(id = R.color.DarkGray),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
fun ReserveSummaryBody(modifier: Modifier) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.reserve_slot),
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
            Text(
                text = stringResource(id = R.string.begin_time),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.day),
                        fontStyle = FontStyle.Italic
                    )
                    Card() {
                        Text(
                            text = "${viewModel.selectedDay.get(Calendar.DAY_OF_MONTH)}/${viewModel.selectedDay.get(Calendar.MONTH)}/${viewModel.selectedDay.get(Calendar.YEAR)}",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.time),
                        fontStyle = FontStyle.Italic
                    )
                    Card() {
                        Text(
                            text = "${viewModel.selectedDay.get(Calendar.HOUR_OF_DAY)}:${viewModel.selectedDay.get(Calendar.MINUTE)}",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.pickup_time),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.day),
                        fontStyle = FontStyle.Italic
                    )
                    Card() {
                        Text(
                            text = "${viewModel.getPickUpTime().get(Calendar.DAY_OF_MONTH)}/${viewModel.getPickUpTime().get(Calendar.MONTH)}/${viewModel.getPickUpTime().get(Calendar.YEAR)}",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.time),
                        fontStyle = FontStyle.Italic
                    )
                    Card() {
                        Text(
                            text = "${viewModel.getPickUpTime().get(Calendar.HOUR_OF_DAY)}:${viewModel.getPickUpTime().get(Calendar.MINUTE)}",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.selected_slot),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.floor),
                        fontStyle = FontStyle.Italic
                    )
                    Card(
                        modifier = Modifier
                            .size(60.dp)
                            .aspectRatio(1f)
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            Text(
                                text = viewModel.selectedFloor?.floorNumber.toString(),
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.sector),
                        fontStyle = FontStyle.Italic
                    )
                    Card(
                        modifier = Modifier
                            .size(60.dp)
                            .aspectRatio(1f)
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            Text(
                                text = viewModel.selectedSector?.sectorNumber.toString(),
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.slot),
                        fontStyle = FontStyle.Italic
                    )
                    Card(
                        modifier = Modifier
                            .size(height = 60.dp, width = 150.dp)
                    ) {
                        val selectedSlot: Slot = if(viewModel.selectedSlot == null)
                            StandardSlot(1, 0f)
                        else
                            viewModel.selectedSlot!!
                        SlotCard(slot = selectedSlot, modifier = Modifier.fillMaxSize())
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.estimated_price),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.price),
                        fontStyle = FontStyle.Italic
                    )
                    Card() {
                        Text(
                            text = "0,00 €",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
//@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
//@Preview(device = Devices.PIXEL_3, showBackground = true)
@Composable
fun ReserveSummaryPreview() {
    ParkingNextTheme {
        ReserveSummary({}, {}, {})
    }
}