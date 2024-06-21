package com.example.parkingnext.data

import com.example.parkingnext.model.Car
import com.example.parkingnext.model.Floor
import com.example.parkingnext.model.ParkingTime
import com.example.parkingnext.model.Reservation
import com.example.parkingnext.model.Sector
import com.example.parkingnext.model.Slot
import com.example.parkingnext.model.User
import java.util.Date

interface DAO {
    fun register(newUser: User): User

    fun login(username: String, password: String): User

    fun logout()

    fun getCurrentUser(): User

    fun reserve(slot: Slot, car: Car, beginTime: Date, parkingTime: ParkingTime)

    fun getCarsOfUser() : List<Car>

    fun getFloors(): List<Floor>

    fun getSectors(floor: Floor): List<Sector>

    fun getSlots(sector: Sector): List<Slot>

    fun getNumberOfAvailableSlots(sector: Sector): Int

    fun getAvailableParkingTimes(slot: Slot): List<ParkingTime>

    fun addCar(car: Car) : Car

    fun getCurrentReservations(): List<Reservation>

    fun getReservationHistory(): List<Reservation>
}