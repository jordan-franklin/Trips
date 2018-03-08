package com.rebeccahenderson.trips.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.github.kittinunf.result.Result
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.adapters.TripsListAdapter
import com.rebeccahenderson.trips.models.Trip
import com.rebeccahenderson.trips.services.TravefyAPI
import kotlinx.android.synthetic.main.activity_trips.*

class TripsListActivity : AppCompatActivity() {

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TripsListAdapter

    private val onItemClickListener = object : TripsListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            startActivity(TripDaysListActivity.newIntent(this@TripsListActivity, position))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        tripList.layoutManager = staggeredLayoutManager

        loadTrips()
    }

    fun setupAdapter(trips: List<Trip>) {
        adapter = TripsListAdapter(this, trips)
        tripList.adapter = adapter
        adapter.setOnItemClickListener(onItemClickListener)
    }

    fun loadTrips() {
        tripListProgress.visibility = View.VISIBLE
        tripList.visibility = View.GONE
        TravefyAPI.getTrips { request, response, result ->
            when(result) {
                is Result.Success -> {
                    tripListProgress.visibility = View.GONE
                    tripList.visibility = View.VISIBLE
                    setupAdapter(result.value)
                }
                is Result.Failure -> {
                    println("Result $response")
                }
            }
        }
    }
}
