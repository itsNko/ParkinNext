package com.example.parkingnext.model

import android.icu.util.Calendar

class Reservation(val user: User, val car: Car, val floor: Floor, val sector: Sector, val slot: Slot, val beginTime: Calendar, var duration: ParkingTime) {
}