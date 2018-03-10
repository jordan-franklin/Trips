package com.rebeccahenderson.trips.models

import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat


/**
 * Created by becky on 3/7/18.
 */

data class Day(var Id: Int,
			   var Title: String?,
			   var Date: Date?,
			   var TripEvents: List<Event>) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readInt(),
			parcel.readString(),
			Date(parcel.readLong()),
			parcel.createTypedArrayList(Event))

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(Id)
		parcel.writeString(Title)
		parcel.writeLong(Date?.time ?: 0)
		parcel.writeTypedList(TripEvents)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Day> {
		override fun createFromParcel(parcel: Parcel): Day {
			return Day(parcel)
		}

		override fun newArray(size: Int): Array<Day?> {
			return arrayOfNulls(size)
		}
	}

	class ListDeserializer : ResponseDeserializable<List<Day>> {

		inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

		override fun deserialize(content: String) = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
				.create()
				.fromJson<List<Day>>(content)
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