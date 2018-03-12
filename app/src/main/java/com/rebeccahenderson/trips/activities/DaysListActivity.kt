package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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
			val day = adapter.days[position]
			val tripEvents = day.TripEvents
			if (tripEvents != null && tripEvents.count() >= 0)
				startActivity(EventsListActivity.newIntent(this@DaysListActivity, day))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_days)

        layoutManager = LinearLayoutManager(this)
        tripDaysList.layoutManager = layoutManager

		setSupportActionBar(toolbar);
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		setupTripCover()
		loadTripDays()

		collapsingToolbar?.setTitle(trip.Name);
    }

	override fun onResume() {
		super.onResume()
		tripDaysListProgress.visibility = View.GONE
	}

    fun setupAdapter(days: List<Day>?) {
		val days = days
		val daysWithoutInfo = days?.drop(1) //the first entry is the information entry which shouldn't be shown

		adapter = DaysListAdapter(this, daysWithoutInfo ?: listOf(), trip)
		tripDaysList.adapter = adapter
		adapter.setOnItemClickListener(onItemClickListener)
	}

	fun handleLoadError() {
		val alertBuilder = AlertDialog.Builder(this)
		alertBuilder.setMessage(R.string.loadingDaysErrorMessage)
				.setPositiveButton(R.string.loadingErrorOK, null)
				.create()
				.show()

	}

	fun setupTripCover() {
		trip = intent.getParcelableExtra(EXTRA_TRIP_ID)
		Picasso.with(this)
				.load(trip.TripCoverPhotoUrl)
				.into(tripDayImage, PicassoPalette.with(trip.TripCoverPhotoUrl.toString(), tripDayImage))
	}

	fun startLoadIndicators() {
		tripDaysListProgress.visibility = View.VISIBLE
		tripDaysList.visibility = View.GONE
	}

	fun stopLoadIndicators() {
		tripDaysListProgress.visibility = View.GONE
		tripDaysList.visibility = View.VISIBLE
	}

    fun loadTripDays() {
        startLoadIndicators()
        TravefyAPI.getTrip(trip.Id) { request, response, result ->
			when(result) {
				is Result.Success -> {
					stopLoadIndicators()

					trip = result.value
					setupAdapter(trip.TripDays)
				}
				is Result.Failure -> {
					handleLoadError()
				}
			}
        }
    }
}