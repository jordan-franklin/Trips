package com.rebeccahenderson.trips.models

import java.util.*

/**
 * Created by becky on 3/7/18.
 */
data class TripDay(var Id: Int, var Title: String, var Date: Date, var TripEvents: Array<TripEvent>) {
}