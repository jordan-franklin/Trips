package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.github.florent37.picassopalette.PicassoPalette
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.adapters.DaysListAdapter
import com.rebeccahenderson.trips.models.Trip
import com.rebeccahenderson.trips.services.TravefyAPI
import kotlinx.android.synthetic.main.activity_days.*
import com.github.kittinunf.result.Result
import com.rebeccahenderson.trips.models.Day
import com.squareup.picasso.Picasso

/**
 * Created by becky on 3/7/18.
 */
class DaysListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TRIP_ID = "trip"

        fun newIntent(context: Context, trip: Trip): Intent {
            val intent = Intent(context, DaysListActivity::class.java)
            intent.putExtra(EXTRA_TRIP_ID, trip)
            return intent
        }
    }

    lateinit private var trip: Trip
    lateinit private var layoutManager: LinearLayoutManager
    lateinit private var adapter: DaysListAdapter

    private val onItemClickListener = object : DaysListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            startActivity(EventsListActivity.newIntent(this@DaysListActivity, adapter.days[position]))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_days)

        layoutManager = LinearLayoutManager(this)
        tripDaysList.layoutManager = layoutManager

		setSupportActionBar(toolbar);
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		loadTripDays()

		collapsingToolbar?.setTitle(trip.Name);
    }

    fun setupAdapter(days: List<Day>?) {
		var filteredDays = days?.filter { it.Date != null }
		adapter = DaysListAdapter(this, filteredDays ?: listOf(), trip)
		tripDaysList.adapter = adapter
		adapter.setOnItemClickListener(onItemClickListener)
	}

    fun loadTripDays() {
		trip = intent.getParcelableExtra(EXTRA_TRIP_ID)
		Picasso.with(this)
				.load(trip.TripCoverPhotoUrl)
				.into(tripDayImage, PicassoPalette.with(trip.TripCoverPhotoUrl.toString(), tripDayImage))

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