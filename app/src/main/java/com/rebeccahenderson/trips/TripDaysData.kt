package com.rebeccahenderson.trips

import java.util.*

/**
 * Created by becky on 3/7/18.
 */

object TripDaysData {
    var tripDates = arrayOf(
            "Friday March 31",
            "Saturday April 1",
            "Sunday April 2",
            "Monday April 3",
            "Tuesday April 4",
            "Wednesday April 5",
            "Thurday April 6",
            "Friday de Janeiro April 7",
            "Saturday April 8",
            "Sunday April 9",
            "Monday April 10",
            "Tuesday April 11",
            "Wednesday April 12",
            "Thurday April 13",
            "Friday Kong April 14",
            "Saturday April 15",
            "Sunday April 16",
            "Monday April 17",
            "Tuesday April 18",
            "Wednesday April 19",
            "Thurday April 20",
            "Friday April 21",
            "Saturday April 22",
            "Sunday April 23",
            "Monday April 24",
            "Tuesday April 25",
            "Wednesday April 26",
            "Thurday April 27",
            "Friday April 28",
            "Saturday April 29")

    fun tripDaysList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (date in tripDates) {
            list.add(date)
        }
        return list
    }
}