package com.rebeccahenderson.trips

import java.util.ArrayList

/**
 * Created by becky on 3/6/18.
 */

object TripsData {
	var tripNames = arrayOf(
			"London",
			"Rome",
			"Venice",
			"Lisbon",
			"Moscow",
			"Seoul",
			"Tokyo",
			"Rio de Janeiro",
			"Fes",
			"New York",
			"Toronto",
			"Mumbai",
			"Paris",
			"Bangkok",
			"Hong Kong",
			"Prague",
			"Melbourne",
			"Cape Town",
			"Seattle",
			"San Francisco",
			"Yosemite",
			"Zurich",
			"Amsterdam",
			"Athens",
			"Zagreb",
			"Puerto Vallarta",
			"Patagonia",
			"Havana",
			"Miami",
			"Oslo")

	fun tripsList(): ArrayList<String> {
		val list = ArrayList<String>()
		for (trip in tripNames) {
			list.add(trip)
		}
		return list
	}
}