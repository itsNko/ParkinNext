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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.data.DAO
import com.example.parkingnext.data.DummyDAO
import com.example.parkingnext.model.Slot
import com.example.parkingnext.ui.theme.ParkingNextTheme

@Composable
fun ParkingSlots(
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
    val dao: DAO = DummyDAO()
    var slots = dao.getSlots(dao.getSectors(dao.getFloors()[0])[0])
    var sectorAmount = 16
    var availableSectorAmount = 8
    Scaffold(
        topBar = { ParkingSlotsTopBar(backButtonOnClick, sectorAmount, availableSectorAmount) },
        bottomBar = { ParkingSlotsBottomBar(
            goBackOnClick = backButtonOnClick,
            nextOnClick = nextButtonOnClick
        )},
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
            text = stringResource(id = R.string.parking_slots),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.weight(2f))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = MaterialTheme.typography.displayMedium.fontWeight, fontSize = 16.sp)) {
                    append(availableSectorAmount.toString())
                }
                withStyle(style = SpanStyle(color = Color.Gray, fontSize = 16.sp)) {
                    append(" / " + sectorAmount.toString())
                }
            },
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ParkingSlotsBottomBar(
    goBackOnClick: () -> Unit,
    nextOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = goBackOnClick,
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(20),
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = stringResource(id = R.string.back_btn),
                fontWeight = FontWeight.Normal,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Button(
            onClick = nextOnClick,
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkerOrange)),
            shape = RoundedCornerShape(20),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = stringResource(id = R.string.next_btn),
                fontWeight = FontWeight.SemiBold,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                color = colorResource(id = R.color.DarkGray),
                modifier = Modifier.padding(top = 2.dp)
            )
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
        Spacer(modifier = Modifier.size(15.dp))
        SectorBrowser()
        Spacer(modifier = Modifier.size(15.dp))
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
            SlotDropDownMenu(
                list = viewModel.getFloors(),
                buttonBackgroundColor = Color.Transparent,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
            )
            SlotDropDownMenu(
                list = viewModel.getSectors(),
                buttonBackgroundColor = colorResource(id = R.color.MainOrange),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
            )
        }
    }
}

@Composable
fun SlotDropDownMenu(
    list: List<kotlin.Any>,
    buttonBackgroundColor: Color,
    modifier: Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val icon = if (isExpanded) {
        Icons.Filled.ArrowDropUp
    } else {
        Icons.Filled.ArrowDropDown
    }

    Button(
        onClick = {
                  isExpanded = !isExpanded
        },
        colors = ButtonDefaults.buttonColors(buttonBackgroundColor),
        shape = RoundedCornerShape(20),
        modifier = modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Sector: 1/3",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false},
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        )
        {
            items(list) {item ->
                DropdownMenuItem(
                    text = { Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Hello")
                                } },
                    onClick = { /* Handle click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Divider()
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
                /*Button(
                    onClick = {}, modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = slots[rowIndex * 2].number.toString()
                    )
                }*/
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp),
                    onClick = {}
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.car_r),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxHeight() //fill the max height
                        .width(1.dp)
                )
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp),
                    onClick = {}
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.car_l),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
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
        Button(
            onClick = sectorLeftOnClick,
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = stringResource(id = R.string.back),
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.move_sector),
            color = Color.Gray
        )
        Button(
            onClick = sectorRightOnClick,
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = stringResource(id = R.string.back),
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
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
//@Preview(name = "Standar", device = Devices.NEXUS_6P, showBackground = true, showSystemUi = false)
//@Preview(name = "Small", device = Devices.PIXEL_4, showBackground = true, showSystemUi = false)
//@Preview(name = "narrow", device = Devices.PIXEL_3, showBackground = true )
@Composable
fun GreetingPreview() {
    ParkingNextTheme {
        ParkingSlots({},{})
    }
}