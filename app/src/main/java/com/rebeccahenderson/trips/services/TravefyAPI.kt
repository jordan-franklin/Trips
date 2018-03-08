package com.rebeccahenderson.trips.services

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.rebeccahenderson.trips.models.Trip

/**
 * Created by becky on 3/7/18.
 */
object TravefyAPI {

	init {
		FuelManager.instance.apply {
			basePath = "https://api.travefy.com/api/v1"
			baseHeaders = mapOf("X-API-PUBLIC-KEY" to "8096ad687ebc47cb97754fa317dcd353",
					"X-USER-TOKEN" to "bf93dcf360b5429c802d80c07e6bc62b")
		}
	}

	fun getTrips(handler: (Request, Response, Result<List<Trip>, FuelError>) -> Unit) {
		"/trips".httpGet().responseObject<List<Trip>>(handler)
	}

	fun getTripDays(tripId: Int, handler: (Request, Response, Result<Trip, FuelError>) -> Unit) {
		"/tripDays".httpGet().header(mapOf("X-TRIP-ID" to "$tripId")).responseObject<Trip>(handler)
	}

	fun getTripDayEvents(tripId: Int, handler: (Request, Response, Result<Trip, FuelError>) -> Unit) {
		"/tripDays".httpGet().header(mapOf("X-TRIP-ID" to "$tripId")).responseObject<Trip>(handler)
	}
}