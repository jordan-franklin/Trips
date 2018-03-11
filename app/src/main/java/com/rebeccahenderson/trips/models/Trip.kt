package com.rebeccahenderson.trips.models

import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by becky on 3/7/18.
 */
data class Trip(val Id: Int,
				val Name: String,
				val TripCoverPhotoUrl: String,
				val IsArchived: Boolean,
				val TripDays: List<Day>?) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readInt(),
			parcel.readString(),
			parcel.readString(),
			parcel.readInt() != 0,
			parcel.createTypedArrayList(Day)) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(Id)
		parcel.writeString(Name)
		parcel.writeString(TripCoverPhotoUrl)
		parcel.writeInt(if (IsArchived) 1 else 0 )
		parcel.writeTypedList(TripDays)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Trip> {
		override fun createFromParcel(parcel: Parcel): Trip {
			return Trip(parcel)
		}

		override fun newArray(size: Int): Array<Trip?> {
			return arrayOfNulls(size)
		}
	}

	class Deserializer : ResponseDeserializable<Trip> {

		inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

		override fun deserialize(content: String) = GsonBuilder()
				.registerTypeAdapter(Date::class.java, DateDeserializer())
				.create()
				.fromJson<Trip>(content)
	}

	class DateDeserializer : JsonDeserializer<Date> {

		override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
			var dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
			try {
				return SimpleDateFormat(dateFormat).parse(json?.asString)
			} catch (e: ParseException) {
				println(e)
			}

			throw JsonParseException("Unparseable date: ${json?.asString}")
		}
	}
}