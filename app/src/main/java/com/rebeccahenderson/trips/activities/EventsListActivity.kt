package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rebeccahenderson.trips.adapters.EventsListAdapter
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.models.Day
import com.rebeccahenderson.trips.models.Event
import com.rebeccahenderson.trips.models.Trip
import kotlinx.android.synthetic.main.activity_events.*

class EventsListActivity : AppCompatActivity() {

    companion object {
		val EXTRA_TRIP_DAY_ID = "trip_day"

        fun newIntent(context: Context, day: Day): Intent {
            val intent = Intent(context, EventsListActivity::class.java)
			intent.putExtra(EXTRA_TRIP_DAY_ID, day)
            return intent
        }
    }

    lateinit private var linearLayoutManager: LinearLayoutManager
    lateinit private var adapter: EventsListAdapter
    lateinit private var day: Day

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        linearLayoutManager = LinearLayoutManager(this)
        dayEventsList.layoutManager = linearLayoutManager

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadDay()
    }

    fun setupAdapter(events: List<Event>?) {
        adapter = EventsListAdapter(this, events ?: listOf<Event>(), day.Date!!)
        dayEventsList.adapter = adapter
    }

    fun loadDay() {
		day = intent.getParcelableExtra<Day>(EXTRA_TRIP_DAY_ID)
		supportActionBar?.title = day.Title

		setupAdapter(day.TripEvents)
    }
}
