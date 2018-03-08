package com.rebeccahenderson.trips.services

import com.github.kittinunf.fuel.core.FuelManager
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
					"X-USER-TOKEN" to "")
		}
	}

	fun getTrips() {
		"/trips".httpGet().responseObject<List<Trip>> { request, response, result ->
			when(result) {
				is Result.Success -> {
					println("Result $response")
				}
				is Result.Failure -> {
					println("Result $response")
				}
			}
		}
	}
}