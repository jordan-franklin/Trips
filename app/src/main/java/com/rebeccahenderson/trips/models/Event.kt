package com.rebeccahenderson.trips.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by becky on 3/7/18.
 */

data class Event(val Id: Int,
                 val Name: String,
                 val StartTimeInMinutes: Int?,
                 val Description: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readValue(Int::class.java.classLoader) as Int?,
            parcel.readString() ?: "") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(Name)
		parcel.writeValue(StartTimeInMinutes)
        parcel.writeString(Description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}