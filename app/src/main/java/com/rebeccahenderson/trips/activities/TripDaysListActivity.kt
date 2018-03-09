package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.adapters.TripDaysListAdapter
import com.rebeccahenderson.trips.models.Trip
import com.rebeccahenderson.trips.services.TravefyAPI
import kotlinx.android.synthetic.main.activity_tripdays.*
import com.github.kittinunf.result.Result
import com.rebeccahenderson.trips.models.TripDay

/**
 * Created by becky on 3/7/18.
 */
class TripDaysListActivity: AppCompatActivity() {

    companion object {
        val EXTRA_TRIP_ID = "trip"

        fun newIntent(context: Context, trip: Trip): Intent {
            val intent = Intent(context, TripDaysListActivity::class.java)
            intent.putExtra(EXTRA_TRIP_ID, trip)
            return intent
        }
    }

    lateinit private var trip: Trip

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TripDaysListAdapter

    private val onItemClickListener = object : TripDaysListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            startActivity(DayEventsListActivity.newIntent(this@TripDaysListActivity, adapter.days[position]))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tripdays)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        tripDaysList.layoutManager = staggeredLayoutManager

        loadTripDays()
    }

    fun setupAdapter(days: List<TripDay>?) {
		var filteredDays = days?.filter { it.Date != null }
        adapter = TripDaysListAdapter(this, filteredDays ?: listOf())
        tripDaysList.adapter = adapter
        adapter.setOnItemClickListener(onItemClickListener)
    }

    fun loadTripDays() {
		trip = intent.getParcelableExtra(EXTRA_TRIP_ID)
		tripName.text = trip.Name

        tripDaysListProgress.visibility = View.VISIBLE
        tripDaysList.visibility = View.GONE

        TravefyAPI.getTrip(trip.Id) { request, response, result ->
			when(result) {
				is Result.Success -> {
					tripDaysListProgress.visibility = View.GONE
					tripDaysList.visibility = View.VISIBLE
					trip = result.value
					setupAdapter(trip.TripDays)
				}
				is Result.Failure -> {
					println("Result $result")
				}
			}
        }
    }
}