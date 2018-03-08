package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.github.kittinunf.result.Result
import com.rebeccahenderson.trips.adapters.DayEventsListAdapter
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.R.id.tripDayName
import com.rebeccahenderson.trips.models.TripDay
import com.rebeccahenderson.trips.models.TripEvent
import com.rebeccahenderson.trips.services.TravefyAPI
import kotlinx.android.synthetic.main.activity_dayevents.*

class DayEventsListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TRIP_ID = "trip_id"
		val EXTRA_TRIP_DAY_ID = "trip_day"

        fun newIntent(context: Context, tripId: Int, tripDay: TripDay): Intent {
            val intent = Intent(context, DayEventsListActivity::class.java)
            intent.putExtra(EXTRA_TRIP_ID, tripId)
			intent.putExtra(EXTRA_TRIP_DAY_ID, tripDay)
            return intent
        }
    }

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: DayEventsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dayevents)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        dayEventsList.layoutManager = staggeredLayoutManager

        loadDay()
    }

    fun setupAdapter(events: List<TripEvent>) {
        adapter = DayEventsListAdapter(this, events)
        dayEventsList.adapter = adapter
    }

    fun loadDay() {
        val tripId = intent.getIntExtra(EXTRA_TRIP_ID, 0)
		val tripDay = intent.getParcelableExtra<TripDay>(EXTRA_TRIP_DAY_ID)
		dayName.text = tripDay.Title

		dayEventsList.visibility = GONE
		dayEventsListProgress.visibility = VISIBLE

		TravefyAPI.getTripDayEvents(tripDay.Id, tripId) { request, response, result ->
			when(result) {
				is Result.Success -> {
					dayEventsListProgress.visibility = GONE
					dayEventsList.visibility = VISIBLE
					setupAdapter(result.value)
				}
				is Result.Failure -> {
					println("Result ${response.responseMessage}")
				}
			}
		}
    }
}
