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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.ui.theme.ParkingNextTheme

@Composable
fun Home(
    userButtonOnClick: () -> Unit,
    reserveButtonOnClick: () -> Unit,
    historyOnClick: () -> Unit,
    settingsOnClick: () -> Unit,
    addCar: () -> Unit,
    modifier: Modifier = Modifier
        .safeDrawingPadding()
        .fillMaxSize()
) {
    Scaffold(
        topBar = { HomeTopBar(userButtonOnClick, reserveButtonOnClick) },
        bottomBar = { HomeBottomBar(
            reserveButtonOnClick = reserveButtonOnClick,
            historyOnClick = historyOnClick,
            settingsOnClick = settingsOnClick
        ) },
        modifier = modifier
    ) {
        HomeBody(addCar, modifier = Modifier.padding(it))
    }
}

@Composable
fun HomeTopBar(
    userButtonOnClick: () -> Unit,
    reserveButtonOnClick: () -> Unit
) {
    Surface(
        color = colorResource(id = R.color.MainOrange),
        shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = userButtonOnClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.SupervisedUserCircle,
                        contentDescription = stringResource(id = R.string.user_settings),
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_back),
                    color = Color.White,
                    fontSize = 20.sp
                )
                Text(
                    text = stringResource(id = R.string.home_title),
                    color = Color.White,
                    fontSize = 35.sp,
                    lineHeight = 35.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Button(
                    onClick = reserveButtonOnClick,
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CarRental,
                            contentDescription = null,
                            tint = colorResource(id = R.color.DarkGray),
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Spacer(Modifier.size(10.dp))
                        Text(
                            text = stringResource(id = R.string.reserve_button),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.DarkGray)
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun HomeBottomBar(
    historyOnClick: () -> Unit,
    settingsOnClick: () -> Unit,
    reserveButtonOnClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
                .shadow(
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black,
                    ambientColor = Color.Black,
                    elevation = 10.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(20.dp)
                    ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(id = R.string.home),
                        tint = colorResource(id = R.color.MainOrange),
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                IconButton(onClick = historyOnClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.History,
                        contentDescription = stringResource(id = R.string.home),
                        tint = colorResource(id = R.color.MediumGray),
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                IconButton(
                    onClick = settingsOnClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(id = R.string.home),
                        tint = colorResource(id = R.color.MediumGray),
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Button(
                    onClick = reserveButtonOnClick,
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.MainOrange)),
                    shape = RoundedCornerShape(100),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(10.dp)
                        .shadow(
                            shape = RoundedCornerShape(100),
                            spotColor = Color.Black,
                            ambientColor = Color.Black,
                            elevation = 10.dp
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = stringResource(id = R.string.home),
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBody(
    addCar: () -> Unit,
    modifier: Modifier
) {
    //var userCars = viewModel.getUserCars()
    Surface(
        modifier = modifier
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.ongoing_reservations),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    /*items(userCars) {car ->
                        CarCard(car)
                    }*/
                    item {
                        CarCard()
                        Spacer(Modifier.size(15.dp))
                    }
                    item {
                        CarCard()
                        Spacer(Modifier.size(15.dp))
                    }
                    item {
                        CarCard()
                        Spacer(Modifier.size(15.dp))
                    }
                    item {
                        CarCard()
                        Spacer(Modifier.size(15.dp))
                    }
                }
                Spacer(Modifier.size(15.dp))
                Box {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(80.dp)
                    ) {
                        Card(
                            onClick = addCar,
                            colors = CardDefaults.cardColors(colorResource(R.color.LighterGray)),
                            modifier = Modifier
                                .size(80.dp)
                        ) {
                            Box(Modifier.fillMaxSize()) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    tint = Color.White,
                                    contentDescription = stringResource(id = R.string.add_car),
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .fillMaxSize()
                                        .padding(5.dp)
                                )
                            }
                        }
                        Text(
                            text = stringResource(id = R.string.add_car),
                            modifier = Modifier
                                .padding(top = 5.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.size(20.dp))
            Reservations()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarCard() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
    ) {
        Card(
            onClick = {},
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(2.dp, colorResource(id = R.color.LighterGray)),
            modifier = Modifier
                .size(80.dp)
        ) {
            Box(Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Filled.DirectionsCar,
                    tint = colorResource(id = R.color.LightGray),
                    contentDescription = stringResource(id = R.string.add_car),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .padding(15.dp)
                )
            }
        }
        Text(
            text = stringResource(id = R.string.add_car),
            modifier = Modifier
                .padding(top = 5.dp)
        )
    }
}

@Composable
fun Reservations() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            ReservationCard()
        }
    }
}

@Composable
fun ReservationCard() {
    Card(
        colors = CardDefaults.cardColors(colorResource(id = R.color.LighterGray)),
        modifier = Modifier
            .aspectRatio(3f)
            .fillMaxWidth()
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val maxHeight = constraints.maxHeight.toFloat()
            val maxWidth = constraints.maxWidth.toFloat()

            val dynamicSize = minOf(maxHeight, maxWidth)

            Row {
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
                            id = R.color.PurpleCar
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
                            tint = colorResource(id = R.color.StarGreen),
                            modifier = Modifier.size((dynamicSize / 15).dp)
                        )
                        Text(
                            text = "4.3",
                            fontSize = (dynamicSize / 25).sp,
                            modifier = Modifier.padding(
                                top = (dynamicSize / 80).dp,
                                start = (dynamicSize / 50).dp
                            )
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = (dynamicSize / 20).sp,
                                )
                            ) {
                                append("Car 1: 1111-ABC")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = (dynamicSize / 26).sp
                                )
                            ) {
                                append("\n Recover time, place, price...")
                            }
                        }
                    )
                    Row {
                        Card(
                            colors = CardDefaults.cardColors(colorResource(id = R.color.LightGray)),
                            shape = RoundedCornerShape((dynamicSize / 50).dp),
                            modifier = Modifier
                                .padding(end = (dynamicSize / 30).dp)
                        ) {
                            Text(
                                text = "Remain time",
                                fontSize = (dynamicSize / 28).sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .padding((dynamicSize / 50).dp)
                            )
                        }
                        Card(
                            colors = CardDefaults.cardColors(colorResource(id = R.color.LightGray)),
                            shape = RoundedCornerShape((dynamicSize / 50).dp)
                        ) {
                            Text(
                                text = "0,00 €",
                                fontSize = (dynamicSize / 28).sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .padding((dynamicSize / 50).dp)
                            )
                        }
                    }
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.edit_square),
                contentDescription = stringResource(
                    id = R.string.edit_reservation
                ),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size((dynamicSize / 10).dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview(showBackground = true)
//@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
@Preview(device = Devices.PIXEL_3, showBackground = true)
@Preview(device = Devices.PIXEL_7_PRO, showBackground = true)
@Composable
fun HomePreview() {
    ParkingNextTheme {
        Home({}, {}, {}, {}, {})
    }
}