package com.example.parkingnext.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.parkingnext.R
import com.example.parkingnext.data.DAO
import com.example.parkingnext.data.DummyDAO
import com.example.parkingnext.model.Slot
import com.example.parkingnext.ui.theme.ParkingNextTheme

@Composable
fun ParkingSlots(
    modifier: Modifier = Modifier
        .safeDrawingPadding()
        .padding(
            top = 0.dp,
            start = 30.dp,
            bottom = 30.dp,
            end = 30.dp
        )
) {
    val dao: DAO = DummyDAO()
    var slots = dao.getSlots(dao.getSectors(dao.getFloors()[0])[0])
    Scaffold(
        topBar = { ParkingSlotsTopBar({}) },
        bottomBar = { ParkingSlotsBottomBar() },
        modifier = modifier
    ) {
        SlotSearcher(slots, Modifier.padding(it))
    }
}

@Composable
fun ParkingSlotsTopBar(backButtonOnClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = backButtonOnClick,
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Icon()
            }
        }

        Text("Parking Slots",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f))

        Text("8/17",
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(1f))
    }
}

@Composable
fun ParkingSlotsBottomBar() {
    Row() {
        Button(onClick = {}) {
            Text("< Go back")
        }
        Button(onClick = {}) {
            Text("Next >")
        }
    }
}

@Composable
fun SlotSearcher(
    slots: List<Slot>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SectorAndFloorDropdown()
        SlotList(slots,
            modifier = Modifier.weight(1.0f))
        SectorBrowser()
    }
}

@Composable
fun SectorAndFloorDropdown() {
    Row() {
        Text("To Delete")
    }
}

@Composable
fun SlotList(
    slots: List<Slot>,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        GradientDivider(
            colors = listOf(Color.Transparent, Color.Gray, Color.Transparent),
            thickness = 1.dp
        )
        repeat(slots.size / 2) { rowIndex ->
            Row(modifier = Modifier.weight(1f)) {
                Button(
                    onClick = {}, modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = slots[rowIndex * 2].number.toString()
                    )
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxHeight() //fill the max height
                        .width(1.dp)
                )
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.car_l),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            GradientDivider(
                colors = listOf(Color.Transparent, Color.Gray, Color.Transparent),
                thickness = 1.dp
            )
        }
    }
}

@Composable
fun SectorBrowser() {
    Row() {
        Button(onClick = {}) {
            Text("<")
        }
        Text("Move Sector")
        Button(onClick = {}) {
            Text(">")
        }
    }
}

@Composable
fun GradientDivider(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
) {
    Canvas(
        modifier = modifier
            .height(thickness)
            .fillMaxWidth()
    ) {
        val gradient = Brush.horizontalGradient(
            colors = colors,
            startX = 0f,
            endX = size.width
        )

        drawRect(
            brush = gradient,
            topLeft = Offset(0f, 0f),
            size = Size(size.width, size.height)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParkingNextTheme {
        ParkingSlots()
    }
}