package com.example.parkingnext.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkingnext.ui.theme.ParkingNextTheme
import com.example.parkingnext.R
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.parkingnext.model.Car

@Composable
fun Home(
    userButtonOnClick: () -> Unit,
    reserveButtonOnClick: () -> Unit,
    historyOnClick: () -> Unit,
    settingsOnClick: () -> Unit,
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
        HomeBody(modifier = Modifier.padding(it))
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
fun HomeBody(modifier: Modifier) {
    //var userCars = viewModel.getUserCars()
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.ongoing_reservations)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    /*items(userCars) {car ->
                        CarCard(car)
                    }*/
                }
                Column() {
                    /*
                    Card(
                        onClick = {},
                        shape = RoundedCornerShape(20.dp),
                        colors = colorResource(R.color.MediumGray),
                        modifier = Modifier
                            .size(80.dp)
                            .aspectRatio(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            tint = Color.White,
                            contentDescription = stringResource(id = R.string.add_car),
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Text(stringResource(id = R.string.add_car))*/
                }
            }
        }
    }
}

@Composable
fun CarCard(car: Car) {
    Column() {
        Button(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add_car)
            )
        }
        Text(stringResource(id = R.string.add_car))
    }
}

@Composable
fun ReservationCard() {

}

@Preview(showBackground = true)
@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
@Preview(device = Devices.PIXEL_3, showBackground = true)
@Composable
fun HomePreview() {
    ParkingNextTheme {
        Home({}, {}, {}, {})
    }
}