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
import android.icu.util.Calendar
import com.example.parkingnext.model.Floor
import com.example.parkingnext.model.ParkingTime
import com.example.parkingnext.model.Sector

class ParkingNextViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ParkingNextUIState(""))
    val uiState: StateFlow<ParkingNextUIState> = _uiState.asStateFlow()
    private var currentUser: User? = null
    private val dao: DAO = DummyDAO()
    var selectedCar: Car? by mutableStateOf(null)
    var selectedDay: Calendar by mutableStateOf(Calendar.getInstance())
    var selectedDuration: ParkingTime by mutableStateOf(ParkingTime.MINUTES_15)
    var selectedFloor: Floor? by mutableStateOf(null)
    var selectedSector: Sector? by mutableStateOf(null)

    init {
        currentUser = dao.getUser("")
        selectedFloor = dao.getFloors()[0]
        selectedSector = dao.getSectors(selectedFloor!!)[0]
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

    fun getCurrentDate(): Calendar {
        return dao.getCurrentDate()
    }

    fun getFloors(): List<Floor> {
        return dao.getFloors()
    }

    fun getSectors(): List<Sector> {
        return dao.getSectors(selectedFloor!!)
    }
}