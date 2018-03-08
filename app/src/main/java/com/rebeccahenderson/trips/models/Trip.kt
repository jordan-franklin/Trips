package com.rebeccahenderson.trips.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

/**
 * Created by becky on 3/7/18.
 */
data class Trip(val Id: Int,
				val Name: String,
				val TripCoverPhotoUrl: String,
				val TripDays: Array<TripDay>) {
	class Deserializer : ResponseDeserializable<Trip> {
		override fun deserialize(reader: Reader) = Gson().fromJson(reader, Trip::class.java)
	}

	class ListDeserializer : ResponseDeserializable<List<Trip>> {
		override fun deserialize(reader: Reader): List<Trip> {
			val type = object : TypeToken<List<Trip>>() {}.type
			return Gson().fromJson(reader, type)
		}
	}
}