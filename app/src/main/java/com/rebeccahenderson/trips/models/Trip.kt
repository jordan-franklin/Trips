package com.rebeccahenderson.trips.models

import android.os.Parcel
import android.os.Parcelable
/**
 * Created by becky on 3/7/18.
 */
data class Trip(val Id: Int,
				val Name: String,
				val TripCoverPhotoUrl: String,
				val TripDays: Array<TripDay>?) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readInt(),
			parcel.readString(),
			parcel.readString(),
			parcel.createTypedArray(TripDay)) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(Id)
		parcel.writeString(Name)
		parcel.writeString(TripCoverPhotoUrl)
		parcel.writeTypedArray(TripDays, flags)
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
}