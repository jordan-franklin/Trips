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

    var eventDescriptionHTML = "<p>This is an <strong>event</strong> description. Here is a <a href='https://www.travefy.com'>link</a>.</p><br/><p>Everything was in confusion in the Oblonskys’ house. The wife had discovered that the husband was carrying on an intrigue with a French girl, who had been a governess in their family, and she had announced to her husband that she could not go on living in the same house with him. This position of affairs had now lasted three days, and not only the husband and wife themselves, but all the members of their family and household, were painfully conscious of it. Every person in the house felt that there was no sense in their living together, and that the stray people brought together by chance in any inn had more in common with one another than they, the members of the family and household of the Oblonskys. The wife did not leave her own room, the husband had not been at home for three days. The children ran wild all over the house; the English governess quarreled with the housekeeper, and wrote to a friend asking her to look out for a new situation for her; the man-cook had walked off the day before just at dinner time; the kitchen-maid, and the coachman had given warning.</p>"

    fun dayEventsList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (event in dayEvents) {
            list.add(event)
        }
        return list
    }
}