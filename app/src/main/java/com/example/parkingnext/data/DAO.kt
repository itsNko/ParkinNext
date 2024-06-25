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

    /**
     * @return Returns the floors of the parking.
     */
    fun getFloors(): List<Floor>

    /**
     * @param floor The floor where to get the floors from.
     * @return Returns the sectors of a given floor.
     */
    fun getSectors(floor: Floor): List<Sector>

    /**
     * @param sector The sector where to get the slots from.
     * @return Returns the slots of a given sector.
     */
    fun getSlots(sector: Sector): List<Slot>

    /**
     * @param username The unique username of a user
     * @return The user that corresponds to the username.
     */
    fun getUser(username: String): User

    /**
     * @param user The user from which to get their cars from.
     * @return A list of the cars registered by the user.
     */
    fun getCarsOfUser(user: User) : List<Car>
}