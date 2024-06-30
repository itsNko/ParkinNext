package com.example.parkingnext.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.model.Car
import com.example.parkingnext.model.ElectricCar
import com.example.parkingnext.model.SpecialCar
import com.example.parkingnext.ui.theme.ParkingNextTheme
import com.example.parkingnext.viewModel

@Composable
fun ReserveCar(
    backButtonOnClick: () -> Unit,
    nextButtonOnClick: () -> Unit,
    addCarOnClick: () -> Unit,
    modifier: Modifier = Modifier
        .safeDrawingPadding()
        .padding(
            top = 20.dp,
            bottom = 20.dp,
            start = 30.dp,
            end = 30.dp
        )
    ) {
        val carList = viewModel.getUserCars()
        Scaffold(
            topBar = { ReserveTimeTopBar(backButtonOnClick) },
            bottomBar = { ParkingSlotsBottomBar(
                goBackOnClick = backButtonOnClick,
                nextOnClick = nextButtonOnClick
            ) },
            modifier = modifier
        ) {
            ReserveCarBody(
                addCarOnClick = addCarOnClick,
                modifier = Modifier.padding(it),
                carList = carList
            )
        }
}

@Composable
fun ReserveCarBody(
    modifier: Modifier,
    addCarOnClick: () -> Unit,
    carList: List<Car>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            Text(
                text = stringResource(id = R.string.reserve_slot),
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.car_selection),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
        item {
            AddCarButton(addCarOnClick)
        }
        items(carList) { car ->
            CarCard(
                car = car,
                isSelected = (viewModel.selectedCar == car),
                onClick = {
                    viewModel.selectedCar = car
                }
            )
        }
    }
}

@Composable
fun AddCarButton(
    addCarOnClick: () -> Unit
) {
    Button(
        onClick = addCarOnClick,
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.LightGray)),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f)
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val maxHeight = constraints.maxHeight.toFloat()
            val maxWidth = constraints.maxWidth.toFloat()

            val fontSize = minOf(maxHeight, maxWidth) / 10
            val iconSize = minOf(maxHeight, maxWidth) / 3
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_car),
                    modifier = Modifier.size(iconSize.dp)
                )
                Text(
                    text = stringResource(id = R.string.add_car),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                    fontSize = fontSize.sp,
                    modifier = Modifier.padding(top = (fontSize / 4).dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarCard(
    car: Car,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderStroke =
        if(isSelected) {
            BorderStroke(2.dp, colorResource(id = R.color.MainOrange))
        } else {
            null
        }

    Card(
        colors = CardDefaults.cardColors(colorResource(id = R.color.LightBlue)),
        shape = RoundedCornerShape(15.dp),
        border = borderStroke,
        onClick = onClick,
        modifier = Modifier
            .aspectRatio(3f)
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val maxHeight = constraints.maxHeight.toFloat()
            val maxWidth = constraints.maxWidth.toFloat()

            val dynamicSize = minOf(maxHeight, maxWidth)

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((dynamicSize / 3.5f).dp)
                        .padding((dynamicSize / 20).dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.DirectionsCar,
                        contentDescription = null,
                        tint = colorResource(
                            id = car.carIconColor
                        ),
                        modifier = Modifier
                            .size((dynamicSize / 4).dp)
                            .weight(2f)
                    )
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    ){
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = colorResource(id = R.color.GreenCar),
                            modifier = Modifier.size((dynamicSize / 15).dp)
                        )
                        Text(
                            text = car.carScore.toString(),
                            fontSize = (dynamicSize / 25).sp,
                            modifier = Modifier.padding(
                                top = (dynamicSize / 80).dp,
                                start = (dynamicSize / 50).dp
                            )
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding((dynamicSize / 30).dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = (dynamicSize / 20).sp,
                                )
                            ) {
                                append(car.carName)
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = (dynamicSize / 26).sp
                                )
                            ) {
                                append("\n" + stringResource(id = R.string.model))
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = (dynamicSize / 26).sp
                                )
                            ) {
                                append("\n" + car.carModel)
                            }
                        }
                    )
                    Row {
                        Text(
                            text = stringResource(id = R.string.type),
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = (dynamicSize / 26).sp,
                            modifier = Modifier.padding(end = (dynamicSize / 40).dp)
                        )
                        Card(
                            shape = RoundedCornerShape((dynamicSize / 50).dp),
                            modifier = Modifier.size(
                                height = (dynamicSize / 10).dp,
                                width = (dynamicSize / 3).dp
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                var carType: String = stringResource(id = R.string.standard)
                                if (car is ElectricCar) {
                                    Icon(
                                        imageVector = Icons.Filled.ElectricCar,
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.ElectriCar),
                                        modifier = Modifier.size((dynamicSize / 16).dp)
                                    )
                                    carType = stringResource(id = R.string.electric_car)
                                }
                                else if (car is SpecialCar)
                                    carType = stringResource(id = R.string.special_car)
                                Text(
                                    text = carType,
                                    fontSize = (dynamicSize / 24).sp
                                )
                            }
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .padding((dynamicSize / 20).dp)
            ) {
                Row {
                    Text(
                        text = stringResource(id = R.string.license_plate),
                        fontSize = (dynamicSize / 25).sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic
                    )
                    Card(
                        shape = RoundedCornerShape(dynamicSize / 20),
                        modifier = Modifier
                            .size(
                                height = (dynamicSize / 10).dp,
                                width = (dynamicSize / 4).dp
                            )
                            .padding(
                                start = (dynamicSize / 40).dp
                            )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = car.licenseNumber,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = (dynamicSize / 20).sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                Spacer(Modifier.size((dynamicSize / 40).dp))
                Icon(
                    painter = painterResource(id = R.drawable.edit_square),
                    contentDescription = stringResource(
                        id = R.string.edit_car
                    ),
                    modifier = Modifier.size((dynamicSize / 9).dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
//@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
//@Preview(widthDp = 1000, heightDp = 2000, showBackground = true)
//@Preview(device = Devices.PIXEL_3, showBackground = true)
@Composable
fun ReserveCarPreview() {
    ParkingNextTheme {
        ReserveCar({},{},{})
    }
}