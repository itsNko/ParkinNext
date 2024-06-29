package com.example.parkingnext.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessible
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ElectricCar
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.model.ElectricSlot
import com.example.parkingnext.model.Floor
import com.example.parkingnext.model.Sector
import com.example.parkingnext.model.ShortTimeSlot
import com.example.parkingnext.model.Slot
import com.example.parkingnext.model.SpecialSlot
import com.example.parkingnext.model.StandardSlot
import com.example.parkingnext.ui.theme.ParkingNextTheme
import kotlinx.coroutines.delay

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
    val slots = viewModel.getSlots()
    val sectorAmount = 16
    val availableSectorAmount = 8
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
                    append(" / $sectorAmount")
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
        var rightAnimation by remember {
            mutableStateOf(false)
        }
        SectorAndFloorDropdown(Modifier.padding(top = 10.dp, bottom = 10.dp))
        SlotList(slots,
            rightAnimation,
            modifier = Modifier.weight(1.0f))
        Spacer(modifier = Modifier.size(15.dp))
        SectorBrowser(
            sectorLeftOnClick = {
                rightAnimation = true
                viewModel.goPreviousSector()
            },
            sectorRightOnClick = {
                rightAnimation = false
                viewModel.goNextSector()
            }
        )
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
                list = viewModel.getSectors(),
                buttonBackgroundColor = Color.Transparent,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
            )
            SlotDropDownMenu(
                list = viewModel.getFloors(),
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
    list: List<Any>,
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
        val dropDownText: String = if (list[0] is Sector) {
            "${stringResource(id = R.string.sector)} " +
                    "${viewModel.selectedSector?.sectorNumber.toString()}" +
                    "/${viewModel.getSectors().size}"
        } else {
            "${stringResource(id = R.string.floor)} " +
                    "${viewModel.selectedFloor?.floorNumber.toString()}" +
                    "/${viewModel.getFloors().size}"
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = dropDownText,
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
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        )
        {
            var i = 1
            var dropText: String
            var onClick: () -> Unit
            Divider()
            list.forEach{
                if (it is Floor) {
                    dropText = "${stringResource(id = R.string.floor)} $i"
                    onClick = {
                        viewModel.selectedFloor = it
                        isExpanded = !isExpanded
                    }
                }
                else {
                    dropText = "${stringResource(id = R.string.sector)} $i"
                    onClick = {
                        viewModel.selectedSector = it as? Sector
                        isExpanded = !isExpanded
                    }
                }
                DropdownMenuItem(
                    text = { Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(dropText)
                    } },
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Divider()
                i++
            }
        }
    }
}

@Composable
fun SlotList(
    slots: List<Slot>,
    turnRight: Boolean,
    modifier: Modifier = Modifier) {

    var currentSlots by remember { mutableStateOf(slots) }
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(slots) {
        isVisible = false
        delay(500) // Duration of the fade-out animation
        currentSlots = slots
        isVisible = true
    }

    val animDuration = 500
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = isVisible,
            enter =
                slideInHorizontally(
                    initialOffsetX = { fullWidth ->
                        if(turnRight) -fullWidth else fullWidth},
                    animationSpec = tween(durationMillis = animDuration)
                ) + fadeIn(animationSpec = tween(durationMillis = animDuration))
            ,
            exit =
                slideOutHorizontally(
                    targetOffsetX = { fullWidth ->
                        if(turnRight) fullWidth else -fullWidth
                    },
                    animationSpec = tween(durationMillis = animDuration - 100)
                ) + fadeOut(animationSpec = tween(durationMillis = animDuration - 100))
        ) {
            Column(
                modifier = modifier.padding(8.dp)
            ) {
                GradientDivider(
                    colors = listOf(Color.Transparent, Color.Gray, Color.Transparent),
                    thickness = 1.dp
                )
                var index = 0
                for (i in 0..<slots.size / 2) {
                    Row(modifier = Modifier.weight(1f)) {
                        SlotCard(
                            slot = currentSlots[index++],
                            modifier = modifier
                                .weight(1f)
                                .fillMaxSize()
                        )
                        Divider(
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxHeight() //fill the max height
                                .width(1.dp)
                        )
                        SlotCard(
                            slot = currentSlots[index++],
                            modifier = modifier
                                .weight(1f)
                                .fillMaxSize()
                        )
                    }
                    GradientDivider(
                        colors = listOf(Color.Transparent, Color.Gray, Color.Transparent),
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@Composable
fun SlotCard(
    slot: Slot,
    modifier: Modifier
) {
    val isSelected = viewModel.selectedSlot == slot

    val border = if(isSelected) BorderStroke(2.dp, colorResource(id = R.color.MainOrange)) else null
    Surface(
        modifier = modifier.padding(4.dp),
        shape = RoundedCornerShape(10.dp),
        border = border,
        onClick = {
            if(slot.isAvailable)
                viewModel.selectedSlot = slot
        }
    ) {
        if (!slot.isAvailable) {
            val imageID =
                if (slot.number % 2 == 0)
                    R.drawable.car_l
                else
                    R.drawable.car_r
            Image(
                painter = painterResource(id = imageID),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            var slotNumberText = slot.number.toString()
            if (slotNumberText.length == 1)
                slotNumberText = "0$slotNumberText"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                val icon  = when (slot) {
                    is ElectricSlot -> {
                        Icons.Filled.ElectricCar
                    }

                    is SpecialSlot -> {
                        Icons.Filled.Accessible
                    }

                    else -> {
                        ImageVector.vectorResource(id = R.drawable.min_15_icon)
                    }
                }

                var cardColor  = when (slot) {
                    is ElectricSlot -> {
                        colorResource(id = R.color.ElectricSlot)
                    }

                    is SpecialSlot -> {
                        colorResource(id = R.color.SpecialSlot)
                    }

                    is ShortTimeSlot -> {
                        colorResource(id = R.color.ShortTimeSlot)
                    }

                    else -> {
                        Color.Gray
                    }
                }

                if(isSelected)
                    cardColor = colorResource(id = R.color.MainOrange)

                Text(
                    text = slotNumberText,
                    fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                    fontSize = 24.sp,
                    color = cardColor,
                )
                if (slot !is StandardSlot) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = cardColor,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(5.dp)
                    )
                }
            }
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
//@Preview(name = "Standard", device = Devices.NEXUS_6P, showBackground = true, showSystemUi = false)
//@Preview(name = "Small", device = Devices.PIXEL_4, showBackground = true, showSystemUi = false)
//@Preview(name = "narrow", device = Devices.PIXEL_3, showBackground = true )
@Composable
fun GreetingPreview() {
    ParkingNextTheme {
        val slot = ShortTimeSlot(8, 2.0f)
        slot.isAvailable = true
        SlotCard(slot = slot, modifier = Modifier.size(80.dp, 40.dp))
    }
}