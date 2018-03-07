package com.rebeccahenderson.trips

import com.rebeccahenderson.trips.TripDaysData.tripDates
import java.util.ArrayList

/**
 * Created by becky on 3/7/18.
 */
object DayEventsData {
    var dayEvents = arrayOf(
            "Hotel Reservation",
            "Breakfast Restaurant Reservation",
            "Walking Tour",
            "Museum Visit",
            "Coffee Reservation",
            "Shopping",
            "Bus Trip",
            "Lunch Restaurant Reservation",
            "Bus Trip",
            "Workout",
            "Walk in Park",
            "Afternoon Snack Reservation",
            "Spa Reservation",
            "Facial",
            "Cooking Class",
            "Lecture",
            "Tea Reservation",
            "Zoo Reservation",
            "Outdoor Market Trip",
            "Bus Trip",
            "Nap Time",
            "Bus Trip",
            "Pre-Dinner Drinks Reservation",
            "Play Tickets",
            "Dinner Restaurant Reservation",
            "Bus Trip",
            "Dessert Restaurant Reservation",
            "Art Gallery Opening Tickets",
            "Club Entry Tickets",
            "Uber Reservation")

    fun dayEventsList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (event in dayEvents) {
            list.add(event)
        }
        return list
    }
}