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
import com.example.parkingnext.model.exceptions.ConfirmPasswordException
import com.example.parkingnext.model.exceptions.EmailAlreadyExistsException
import com.example.parkingnext.model.exceptions.EmailFormatException
import com.example.parkingnext.model.exceptions.InvalidDateException
import com.example.parkingnext.model.exceptions.InvalidSlotException
import com.example.parkingnext.model.exceptions.PasswordLengthException
import com.example.parkingnext.model.exceptions.UserNotLoggedException
import com.example.parkingnext.ui.AlertErrorDialog
import com.example.parkingnext.ui.Home
import com.example.parkingnext.ui.Login
import com.example.parkingnext.ui.Register
import com.example.parkingnext.ui.ReserveSummary

lateinit var viewModel: ParkingNextViewModel
private lateinit var alertIcon: ImageVector
private lateinit var alertTitle: String
private lateinit var alertText: String
private lateinit var alertDismiss: () -> Unit
private lateinit var alertAccept: () -> Unit
private var showLoginWarning = false
private var loginWarningText = "Incorrect email or password."
private var showRegisterWarning = false
private var registerWarningText = ""

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
    Login,
    Register,
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
                    navController.navigate(ParkingNextScreen.Login.name)
                },
                registerButtonOnClick = {
                    navController.navigate(ParkingNextScreen.Register.name)
                }
            )
        }
        composable(route = ParkingNextScreen.Register.name) {
            Register(
                signUp = {
                    /*
                    try{
                        viewModel.register()
                        showRegisterWarning = false
                        navController.navigate(ParkingNextScreen.Home.name)
                    } catch(e: ConfirmPasswordException) {
                        showRegisterWarning = true
                        registerWarningText = "Password and confirm password do not match."
                        navController.navigate(ParkingNextScreen.Register.name)
                    } catch(e: EmailFormatException) {
                        showRegisterWarning = true
                        registerWarningText = "Email format is incorrect."
                        navController.navigate(ParkingNextScreen.Register.name)
                    } catch(e: PasswordLengthException) {
                        showRegisterWarning = true
                        registerWarningText = "Password must have at least 8 characters."
                        navController.navigate(ParkingNextScreen.Register.name)
                    } catch(e: EmailAlreadyExistsException) {
                        showRegisterWarning = true
                        registerWarningText = "Email already is registered."
                        navController.navigate(ParkingNextScreen.Register.name)
                    }*/
                         navController.navigate(ParkingNextScreen.Home.name)
                },
                login = {
                    navController.navigate(ParkingNextScreen.Login.name)
                },
                googleSignUp = {

                },
                facebookSignUP = {

                }
            )
        }
        composable(route = ParkingNextScreen.Login.name) {
            Login(
                signIn = {
                    /*
                    try{
                        viewModel.login()
                        showLoginWarning = false
                        navController.navigate(ParkingNextScreen.Home.name)
                    } catch (e: Exception) {
                        showLoginWarning = true
                        navController.navigate(ParkingNextScreen.Login.name)
                    }*/
                         navController.navigate(ParkingNextScreen.Home.name)
                },
                register = {
                    navController.navigate(ParkingNextScreen.Register.name)
                },
                forgotPassword = {

                },
                googleSignIn = {

                },
                facebookSignIn = {

                },
                showWarning = showLoginWarning,
                warningText = loginWarningText
            )
        }
        composable(route = ParkingNextScreen.Home.name) {
            Home(
                reserveButtonOnClick = {
                                       navController.navigate(ParkingNextScreen.ReserveCar.name)
                },
                historyOnClick = {},
                settingsOnClick = {},
                userButtonOnClick = {},
                addCar = {}
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