package com.example.parkingnext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkingnext.ui.ParkingSlots
import com.example.parkingnext.ui.ReserveCar
import com.example.parkingnext.ui.ReserveDate
import com.example.parkingnext.ui.ReserveTime
import com.example.parkingnext.ui.theme.ParkingNextTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkingnext.ui.Welcome


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkingNextTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ParkingNextApp()
                }
            }
        }
    }
}

enum class ParkingNextScreen() {
    Welcome,
    ReserveCar,
    ReserveDate,
    ReserveTime,
    ParkingSlots
}

@Composable
fun ParkingNextApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ParkingNextScreen.Welcome.name
    ) {
        composable(route = ParkingNextScreen.Welcome.name) {
            Welcome(
                loginButtonOnClick = {
                    navController.navigate(ParkingNextScreen.ReserveCar.name)
                },
                registerButtonOnClick = {
                    navController.navigate(ParkingNextScreen.ReserveCar.name)
                }
            )
        }
        composable(route = ParkingNextScreen.ReserveCar.name) {
            ReserveCar(
                backButtonOnClick = {
                                    navController.navigateUp()
                },
                nextButtonOnClick = {
                                    navController.navigate(ParkingNextScreen.ReserveDate.name)
                },
                addCarOnClick = {

                })
        }
        composable(route = ParkingNextScreen.ReserveDate.name) {
            ReserveDate(
                backButtonOnClick = {
                    navController.navigateUp()
                },
                nextButtonOnClick = {
                    navController.navigate(ParkingNextScreen.ReserveTime.name)
                }
            )
        }
        composable(route = ParkingNextScreen.ReserveTime.name) {
            ReserveTime(
                backButtonOnClick = {
                    navController.navigateUp()
                },
                nextButtonOnClick = {
                    navController.navigate(ParkingNextScreen.ParkingSlots.name)
                }
            )
        }
        composable(route = ParkingNextScreen.ParkingSlots.name) {
            ParkingSlots(
                backButtonOnClick = {
                    navController.navigateUp()
                },
                nextButtonOnClick = {

                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParkingNextTheme {
        ParkingNextApp()
    }
}