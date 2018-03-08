package com.rebeccahenderson.trips.models

import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*

/**
 * Created by becky on 3/7/18.
 */

data class TripDay(var Id: Int,
				   var Title: String,
				   var Date: Date?,
				   var TripEvents: Array<TripEvent>?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            if (parcel.readLong() != null) Date(parcel.readLong()) else null,
            parcel.createTypedArray(TripEvent)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(Title)
        parcel.writeLong(Date?.time ?: 0)
        parcel.writeTypedArray(TripEvents, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripDay> {
		override fun createFromParcel(parcel: Parcel): TripDay {
			return TripDay(parcel)
		}

		override fun newArray(size: Int): Array<TripDay?> {
			return arrayOfNulls(size)
		}
	}

	class Deserializer : ResponseDeserializable<TripDay> {
		override fun deserialize(content: String) = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create().fromJson(content, TripDay::class.java)
	}

}