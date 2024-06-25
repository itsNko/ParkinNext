package com.example.parkingnext.model

enum class ParkingTime(val time: String, val measure: String, val timeInMillis: Long) {
    MINUTES_15("15", "mins.", 15 * 60 * 1000),
    MINUTES_30("30", "mins.", 30 * 60 * 1000),
    HOUR_1("1", "hour", 1 * 60 * 60 * 1000),
    HOURS_2("2", "hours", 2 * 60 * 60 * 1000),
    HOURS_3("3", "hours", 3 * 60 * 60 * 1000),
    HOURS_4("4", "hours", 4 * 60 * 60 * 1000),
    HOURS_5("5", "hours", 5 * 60 * 60 * 1000),
    HOURS_6("6", "hours", 6 * 60 * 60 * 1000),
    HOURS_7("7", "hours", 7 * 60 * 60 * 1000),
    HOURS_8("8", "hours", 8 * 60 * 60 * 1000),
    HOURS_9("9", "hours", 9 * 60 * 60 * 1000);

    override fun toString(): String {
        return "$time $measure"
    }
}