package com.example.parkingnext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkingnext.ui.ParkingSlots
import com.example.parkingnext.ui.ReserveCar
import com.example.parkingnext.ui.ReserveDate
import com.example.parkingnext.ui.ReserveTime
import com.example.parkingnext.ui.theme.ParkingNextTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkingnext.ui.ParkingNextViewModel
import com.example.parkingnext.ui.Welcome
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parkingnext.model.exceptions.InvalidDateException
import com.example.parkingnext.model.exceptions.InvalidSlotException
import com.example.parkingnext.model.exceptions.UserNotLoggedException
import com.example.parkingnext.ui.AlertErrorDialog
import com.example.parkingnext.ui.Home
import com.example.parkingnext.ui.ReserveSummary

private lateinit var viewModel: ParkingNextViewModel
private lateinit var alertIcon: ImageVector
private lateinit var alertTitle: String
private lateinit var alertText: String
private lateinit var alertDismiss: () -> Unit
private lateinit var alertAccept: () -> Unit

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

enum class ParkingNextScreen {
    Welcome,
    Home,
    ReserveCar,
    ReserveDate,
    ReserveTime,
    ParkingSlots,
    ReserveSummary,
    Alert
}

@Composable
fun ParkingNextApp() {
    val navController = rememberNavController()
    viewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = ParkingNextScreen.Welcome.name
    ) {
        composable(route = ParkingNextScreen.Welcome.name) {
            Welcome(
                loginButtonOnClick = {
                    navController.navigate(ParkingNextScreen.Home.name)
                },
                registerButtonOnClick = {
                    navController.navigate(ParkingNextScreen.Home.name)
                }
            )
        }
        composable(route = ParkingNextScreen.Home.name) {
            Home(
                reserveButtonOnClick = {
                                       navController.navigate(ParkingNextScreen.ReserveCar.name)
                },
                historyOnClick = {},
                settingsOnClick = {},
                userButtonOnClick = {}
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
                    alertIcon = Icons.Filled.Warning
                    alertTitle = "Function not yet implemented!"
                    alertText = "Keep in touch with future updates!"
                    alertDismiss = {
                        navController.navigateUp()
                    }
                    alertAccept = {
                        navController.navigateUp()
                    }
                    navController.navigate(ParkingNextScreen.Alert.name)
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
                    navController.navigate(ParkingNextScreen.ReserveSummary.name)
                }
            )
        }
        composable(route = ParkingNextScreen.ReserveSummary.name) {
            ReserveSummary(
                backButtonOnClick = {
                                    navController.navigateUp()
                },
                cancelOnClick = { navController.navigate(ParkingNextScreen.Home.name) },
                reserveOnClick = {
                    try {
                        viewModel.reserve()
                        alertIcon = Icons.Filled.Check
                        alertTitle = "Successful Reservation!!"
                        alertText = "Your reservation has been successfully done."
                        alertDismiss = {}
                        alertAccept = {
                            navController.navigate(ParkingNextScreen.Home.name)
                        }
                    } catch (e: UserNotLoggedException) {
                        alertIcon = Icons.Filled.Error
                        alertTitle = "You are not logged in!"
                        alertText = "You must be logged to reserve."
                        alertDismiss = {
                            navController.navigate(ParkingNextScreen.Home.name)
                        }
                        alertAccept = {
                            navController.navigateUp()
                        }
                    } catch (e: InvalidSlotException) {
                        alertIcon = Icons.Filled.Error
                        alertTitle = "Invalid Slot"
                        alertText = "Select an available slot or change the reservation time and duration."
                        alertDismiss = {
                            navController.navigate(ParkingNextScreen.Home.name)
                        }
                        alertAccept = {
                            navController.navigateUp()
                        }
                    } catch (e: InvalidDateException) {
                        alertIcon = Icons.Filled.Error
                        alertTitle = "Invalid date"
                        alertText = "You must select a beginning time after current time."
                        alertDismiss = {
                            navController.navigate(ParkingNextScreen.Home.name)
                        }
                        alertAccept = {
                            navController.navigateUp()
                        }
                    }
                    navController.navigate(ParkingNextScreen.Alert.name)
                }
            )
        }
        composable(route = ParkingNextScreen.Alert.name) {
            AlertErrorDialog(
                icon = alertIcon,
                title = alertTitle,
                content = alertText,
                onDismissRequest = alertDismiss,
                acceptButtonOnClick = alertAccept
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