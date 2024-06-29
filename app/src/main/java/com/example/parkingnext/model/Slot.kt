package com.example.parkingnext.model

abstract class Slot(val number: Int, var price: Float, var isAvailable: Boolean = true) {
}