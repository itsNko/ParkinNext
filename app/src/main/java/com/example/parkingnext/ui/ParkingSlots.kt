package com.example.parkingnext.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.parkingnext.R
import com.example.parkingnext.data.DAO
import com.example.parkingnext.data.DummyDAO
import com.example.parkingnext.model.Slot
import com.example.parkingnext.ui.theme.ParkingNextTheme
import androidx.compose.foundation.layout.size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ParkingSlots(
    backButtonOnClick: () -> Unit,
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
    var sectorAmount = 16
    var availableSectorAmount = 8
    Scaffold(
        topBar = { ParkingSlotsTopBar(backButtonOnClick, sectorAmount, availableSectorAmount) },
        bottomBar = { ParkingSlotsBottomBar() },
        modifier = modifier
    ) {
        SlotSearcher(slots, Modifier.padding(it))
    }
}

@Composable
fun ParkingSlotsTopBar(
    backButtonOnClick: () -> Unit,
    sectorAmount: Int,
    availableSectorAmount: Int,
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
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.Black
                )
            }
        }

        Text(
            text = stringResource(id = R.string.parking_slots),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.weight(2f))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = MaterialTheme.typography.displayMedium.fontWeight)) {
                    append(availableSectorAmount.toString())
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(" / " + sectorAmount.toString())
                }
            },
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(1f)
        )
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
        SectorAndFloorDropdown(Modifier.padding(top = 10.dp, bottom = 10.dp))
        SlotList(slots,
            modifier = Modifier.weight(1.0f))
        SectorBrowser()
    }
}

@Composable
fun SectorAndFloorDropdown(modifier: Modifier = Modifier) {
    Surface(
        color = colorResource(id = R.color.DarkGray),
        shape = RoundedCornerShape(20),
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.weight(1f).padding(2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Sector: 1/3",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize().padding(end = 10.dp).weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.MainOrange)),
                shape = RoundedCornerShape(20),
                modifier = Modifier.weight(1f).padding(2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Floor: 1",
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(end = 5.dp).weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
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
fun SectorBrowser(
    sectorRightOnClick : () -> Unit = {},
    sectorLeftOnClick : () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = sectorLeftOnClick) {
            Text("<")
        }
        Text("Move Sector")
        Button(onClick = sectorRightOnClick) {
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
        ParkingSlots({})
    }
}