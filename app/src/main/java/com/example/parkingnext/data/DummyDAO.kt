package com.example.parkingnext.data

import com.example.parkingnext.R
import com.example.parkingnext.model.Car
import com.example.parkingnext.model.ElectricCar
import com.example.parkingnext.model.Floor
import com.example.parkingnext.model.Parking
import com.example.parkingnext.model.ParkingTime
import com.example.parkingnext.model.Reservation
import com.example.parkingnext.model.Sector
import com.example.parkingnext.model.ShortTimeSlot
import com.example.parkingnext.model.Slot
import com.example.parkingnext.model.SpecialCar
import com.example.parkingnext.model.SpecialSlot
import com.example.parkingnext.model.StandardCar
import com.example.parkingnext.model.StandardSlot
import com.example.parkingnext.model.User
import java.util.Date

class DummyDAO : DAO {
    private val currentUser: User
    private val parking: Parking

    init {
        val cars : List<Car> = listOf(
            StandardCar("Car1", "123ABC", "Seat Ibiza", R.color.GreenCar, 4.1f),
            ElectricCar("Car2", "234BCD", "Tesla Model X", R.color.PurpleCar, 4.3f),
            SpecialCar("Car3", "345CDE", "Audi A8", R.color.BlueCar, 4.3f)
        )

        currentUser = User("id1", "user1", "123", cars)

        var slots = mutableListOf<Slot>()

        for (i in 1..16)
            slots.add(StandardSlot(i, 0.15f))

        var sector1 = Sector(slots)

        slots = mutableListOf<Slot>()
        for (i in 1..16) {
            val slot: Slot = when(i) {
                1, 3, 5, 7, 9 -> SpecialSlot(i, 0.10f)    // Example of different types of cars
                2, 4, 6, 8, 10 ->  StandardSlot(i, 0.15f)
                else -> ShortTimeSlot(i, 0.20f)
            }
            slots.add(slot)
        }

        var sector2 = Sector(slots)
        val floor: Floor = Floor(listOf(sector1, sector2))

        parking = Parking(listOf(floor, floor), listOf(currentUser))
    }

    override fun getFloors(): List<Floor> {
        return parking.floors
    }

    override fun getSectors(floor: Floor): List<Sector> {
        return floor.sectors
    }

    override fun getSlots(sector: Sector): List<Slot> {
        return sector.slots
    }

    override fun getUser(username: String): User {
        return currentUser
    }
    override fun getCarsOfUser(user: User): List<Car> {
        return currentUser.cars
    }
}