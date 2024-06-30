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
import com.example.parkingnext.model.ElectricCar
import com.example.parkingnext.model.ElectricSlot
import com.example.parkingnext.model.Floor
import com.example.parkingnext.model.ParkingTime
import com.example.parkingnext.model.Reservation
import com.example.parkingnext.model.Sector
import com.example.parkingnext.model.ShortTimeSlot
import com.example.parkingnext.model.Slot
import com.example.parkingnext.model.SpecialCar
import com.example.parkingnext.model.SpecialSlot
import com.example.parkingnext.model.StandardCar
import com.example.parkingnext.model.exceptions.InvalidDateException
import com.example.parkingnext.model.exceptions.InvalidSlotException

class ParkingNextViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ParkingNextUIState(""))
    val uiState: StateFlow<ParkingNextUIState> = _uiState.asStateFlow()
    private var currentUser: User? = null
    private var dao: DAO = DummyDAO()
    var selectedCar: Car? by mutableStateOf(null)
    var selectedDay: Calendar by mutableStateOf(Calendar.getInstance())
    var selectedDuration: ParkingTime by mutableStateOf(ParkingTime.MINUTES_15)
    private var selectedPickUpTime: Calendar by mutableStateOf(Calendar.getInstance())
    var selectedFloor: Floor? by mutableStateOf(null)
    var selectedSector: Sector? by mutableStateOf(null)
    var selectedSlot: Slot? by mutableStateOf(null)
    var availableSlots: Int = 0
    var totalSlots: Int = 16

    init {
        currentUser = dao.getUser("")
        selectedFloor = dao.getFloors()[0]
        selectedSector = dao.getSectors(selectedFloor!!)[0]
        selectedCar = getUserCars()[0]
        selectAvailableSlot()
    }

    fun setDAO(newDAO: DAO) {
        dao = newDAO
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

    /**
     * It gets the slots of the selected sector and verifies whether the slots are available or not.
     * @return The slots of the selected sector.
     */
    fun getSlots(): List<Slot> {
        val slots = dao.getSlots(selectedSector!!)
        availableSlots = 0
        slots.forEach { slot ->
            // Get current reservations for the current slot
            val reservations = dao.getCurrentReservations(slot)

            // Assuming selectedPickUpTime and getPickUpTime() are properly initialized elsewhere
            val startTime = selectedPickUpTime
            val endTime = getPickUpTime()  // You need to define how you get the end time
            if(isAvailable(reservations, startTime, endTime)) availableSlots++
            // Check availability
            slot.isAvailable = isAvailable(reservations, startTime, endTime)
            if(((selectedCar is StandardCar || selectedCar is ElectricCar) && slot is SpecialSlot) ||
                (selectedCar is StandardCar && slot is ElectricSlot) ||
                (selectedDuration.name != ParkingTime.MINUTES_15.name && slot is ShortTimeSlot))
                slot.isAvailable = false
        }

        return slots
    }

    private fun selectAvailableSlot() {
        selectedSlot = getSlots().filter { slot -> slot.isAvailable  }[0]
    }

    /**
     * @param targetTimeStart The time the slot is already booked.
     * @param targetTimeEnd The time the slot booking is to be ended.
     * @param startTime The starting time the user wants to book.
     * @param endTime The time the user wants to leave the slot.
     * @return Checks whether the slot is available in the time frame selected by the user.
     */
    private fun isAvailable(targetTimeStart: Calendar, targetTimeEnd: Calendar, startTime: Calendar, endTime: Calendar): Boolean {
        return endTime.timeInMillis <= targetTimeStart.timeInMillis || startTime.timeInMillis >= targetTimeEnd.timeInMillis
    }

    /**
     * @param reservations A list of existing reservations.
     * @param startTime The starting time the user wants to book.
     * @param endTime The time the user wants to leave the slot.
     * @return Checks whether the slot is available in the time frame selected by the user.
     */
    private fun isAvailable(reservations: List<Reservation>, startTime: Calendar, endTime: Calendar): Boolean {
        for (reservation in reservations) {
            if (!(endTime.timeInMillis <= reservation.beginTime.timeInMillis || startTime.timeInMillis >= getPickUpTime(reservation.beginTime, reservation.duration).timeInMillis)) {
                // If there is any overlap, the slot is not available
                return false
            }
        }
        // If no overlaps are found, the slot is available
        return true
    }

    fun goNextSector() {
        var currentI = selectedFloor?.sectors?.indexOf(selectedSector)
        currentI = if (currentI == (selectedFloor?.sectors?.size?.minus(1)))
            0
        else
            currentI!! + 1

        selectedSector = selectedFloor?.sectors?.get(currentI)
    }

    fun goPreviousSector() {
        var currentI = selectedFloor?.sectors?.indexOf(selectedSector)
        currentI = if (currentI == 0)
            selectedFloor?.sectors?.size?.minus(1)
        else
            currentI!! - 1

        selectedSector = currentI?.let { selectedFloor?.sectors?.get(it) }
    }

    private fun getPickUpTime(beginTime: Calendar, duration: ParkingTime): Calendar {
        val pickUpTime = beginTime.clone() as Calendar
        pickUpTime.add(Calendar.MILLISECOND, duration.timeInMillis.toInt())
        return pickUpTime
    }

    fun getPickUpTime(): Calendar {
        return getPickUpTime(selectedDay, selectedDuration)
    }

    /**
     * Make the reservation and store it in the DB.
     */
    fun reserve() {
        val reservation: Reservation
        when {
            currentUser == null -> throw UserNotLoggedException()
            !isAvailable(dao.getCurrentReservations(selectedSlot!!),selectedDay, getPickUpTime()) -> throw InvalidSlotException()
            (selectedDay.timeInMillis + 300000) < dao.getCurrentDate().timeInMillis -> throw InvalidDateException() // 30000 = 5 minutes of error
            else -> {
                reservation = Reservation(
                    currentUser!!,
                    selectedCar!!,
                    selectedFloor!!,
                    selectedSector!!,
                    selectedSlot!!,
                    selectedDay,
                    selectedDuration
                )
                dao.reserve(reservation)
            }
        }
    }
}