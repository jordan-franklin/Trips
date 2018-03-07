package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.rebeccahenderson.trips.adapters.DayEventsListAdapter
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.TripDaysData
import kotlinx.android.synthetic.main.activity_dayevents.*

class DayEventsListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_PARAM_ID = "day_id"

        fun newIntent(context: Context, position: Int): Intent {
            val intent = Intent(context, DayEventsListActivity::class.java)
            intent.putExtra(EXTRA_PARAM_ID, position)
            return intent
        }
    }

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: DayEventsListAdapter
    lateinit private var day: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dayevents)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        dayEventsList.layoutManager = staggeredLayoutManager

        setupAdapter()
        loadDay()
    }

    fun setupAdapter() {
        adapter = DayEventsListAdapter(this)
        dayEventsList.adapter = adapter
    }

    fun loadDay() {
        day = TripDaysData.tripDaysList()[intent.getIntExtra(EXTRA_PARAM_ID, 0)]
        dayName.text = day
    }
}
