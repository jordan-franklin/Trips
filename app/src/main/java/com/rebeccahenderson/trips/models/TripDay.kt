package com.rebeccahenderson.trips.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by becky on 3/7/18.
 */

//inline fun <reified T : Date> Parcel.readDate() =
//        readInt().let { if (it >= 0) enumValues<T>()[it] else null }
//
//inline fun <T : Enum<T>> Parcel.writeDate(value: T?) =
//        writeInt(value?.ordinal ?: -1)

fun Parcel.readDate() {
    Date(readLong())
}

fun Parcel.writeDate(value: Date) {
    writeLong(value.time)
}

data class TripDay(var Id: Int, var Title: String, var Date: Date, var TripEvents: Array<TripEvent>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            TODO("Date"),
            parcel.createTypedArray(TripEvent)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(Title)
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
}