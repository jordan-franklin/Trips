package com.rebeccahenderson.trips.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by becky on 3/7/18.
 */

data class TripEvent(var Id: Int,
                     var Name: String,
                     var Description: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(Name)
        parcel.writeString(Description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripEvent> {
        override fun createFromParcel(parcel: Parcel): TripEvent {
            return TripEvent(parcel)
        }

        override fun newArray(size: Int): Array<TripEvent?> {
            return arrayOfNulls(size)
        }
    }
}