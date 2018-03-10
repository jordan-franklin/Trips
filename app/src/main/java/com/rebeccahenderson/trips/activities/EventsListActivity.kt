package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.rebeccahenderson.trips.adapters.EventsListAdapter
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.models.Day
import com.rebeccahenderson.trips.models.Event
import kotlinx.android.synthetic.main.activity_dayevents.*

class EventsListActivity : AppCompatActivity() {

    companion object {
		val EXTRA_TRIP_DAY_ID = "trip_day"

        fun newIntent(context: Context, day: Day): Intent {
            val intent = Intent(context, EventsListActivity::class.java)
			intent.putExtra(EXTRA_TRIP_DAY_ID, day)
            return intent
        }
    }

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: EventsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dayevents)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        dayEventsList.layoutManager = staggeredLayoutManager

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadDay()
    }

    fun setupAdapter(events: List<Event>?) {
        adapter = EventsListAdapter(this, events ?: listOf<Event>())
        dayEventsList.adapter = adapter
    }

    fun loadDay() {
		val tripDay = intent.getParcelableExtra<Day>(EXTRA_TRIP_DAY_ID)
		supportActionBar?.title = tripDay.Title

		setupAdapter(tripDay.TripEvents)
    }
}