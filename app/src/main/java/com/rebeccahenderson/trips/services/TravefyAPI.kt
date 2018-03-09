package com.rebeccahenderson.trips.services

import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.rebeccahenderson.trips.models.Trip
import com.rebeccahenderson.trips.models.TripDay
import com.rebeccahenderson.trips.models.TripEvent

/**
 * Created by becky on 3/7/18.
 */
object TravefyAPI {

	init {
		FuelManager.instance.apply {
			basePath = "https://api.travefy.com/api/v1"
			baseHeaders = mapOf("X-API-PUBLIC-KEY" to "8096ad687ebc47cb97754fa317dcd353",
					"X-USER-TOKEN" to "")
		}
	}

	fun getTrips(handler: (Request, Response, Result<List<Trip>, FuelError>) -> Unit) {
		"/trips".httpGet().responseObject<List<Trip>>(handler)
	}

	fun getTripDays(tripId: Int, handler: (Request, Response, Result<List<TripDay>, FuelError>) -> Unit) {
		"/tripDays".httpGet()
				.header(mapOf("X-TRIP-ID" to "$tripId"))
				.responseObject<List<TripDay>>(TripDay.ListDeserializer(), handler)
	}

	fun getTripDayEvents(tripDayId: Int, tripId: Int, handler: (Request, Response, Result<List<TripEvent>, FuelError>) -> Unit) {
		"/tripDays/$tripDayId".httpGet()
				.header(mapOf("X-TRIP-ID" to "$tripId"))
				.responseObject<List<TripEvent>>(handler)
	}
}