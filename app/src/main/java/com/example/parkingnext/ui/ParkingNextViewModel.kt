package com.example.parkingnext.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.parkingnext.data.DAO
import com.example.parkingnext.data.DummyDAO
import com.example.parkingnext.model.Car
import com.example.parkingnext.model.User
import com.example.parkingnext.model.exceptions.UserNotLoggedException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ParkingNextViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ParkingNextUIState(""))
    val uiState: StateFlow<ParkingNextUIState> = _uiState.asStateFlow()
    private var currentUser: User? = null
    private val dao: DAO = DummyDAO()
    var selectedCar: Car? by mutableStateOf<Car?>(null)

    init {
        currentUser = dao.getUser("")
    }

    /**
     * @throws UserNotLoggedException When the user is not logged in.
     * @return The cars of the current user.
     */
    fun getUserCars(): List<Car> {
        if (currentUser == null)
            throw UserNotLoggedException()
        return dao.getCarsOfUser(currentUser!!)
    }
}