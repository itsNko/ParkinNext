package com.example.parkingnext.model

class SpecialCar(carName: String,
                 licenseNumber: String,
                 carModel: String,
                 carIconColor: Int,
                 carScore: Float) : Car(carName,
    licenseNumber,
    carModel,
    carIconColor,
    carScore) {
}