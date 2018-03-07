package com.rebeccahenderson.trips.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.adapters.TripDaysListAdapter
import com.rebeccahenderson.trips.TripsData
import kotlinx.android.synthetic.main.activity_tripdays.*

/**
 * Created by becky on 3/7/18.
 */
class TripDaysListActivity: AppCompatActivity() {

    companion object {
        val EXTRA_PARAM_ID = "trip_id"

        fun newIntent(context: Context, position: Int): Intent {
            val intent = Intent(context, TripDaysListActivity::class.java)
            intent.putExtra(EXTRA_PARAM_ID, position)
            return intent
        }
    }

    lateinit private var trip: String

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TripDaysListAdapter

    private val onItemClickListener = object : TripDaysListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            startActivity(DayEventsListActivity.newIntent(this@TripDaysListActivity, position))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tripdays)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        tripDaysList.layoutManager = staggeredLayoutManager

        setupAdapter()
        loadTrip()
    }

    fun setupAdapter() {
        adapter = TripDaysListAdapter(this)
        tripDaysList.adapter = adapter
        adapter.setOnItemClickListener(onItemClickListener)
    }

    fun loadTrip() {
        trip = TripsData.tripsList()[intent.getIntExtra(EXTRA_PARAM_ID, 0)]
        tripName.text = trip
    }
}