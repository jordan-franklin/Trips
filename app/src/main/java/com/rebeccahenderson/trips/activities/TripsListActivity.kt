package com.rebeccahenderson.trips.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import com.github.kittinunf.result.Result
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.adapters.TripsListAdapter
import com.rebeccahenderson.trips.models.Trip
import com.rebeccahenderson.trips.services.TravefyAPI
import kotlinx.android.synthetic.main.activity_trips.*
import android.support.v4.util.Pair
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView

class TripsListActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    lateinit private var linearLayoutManager: LinearLayoutManager
    lateinit private var adapter: TripsListAdapter

    private val onItemClickListener = object : TripsListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            val trip = adapter.trips[position]

			val tripImage = view.findViewById<ImageView>(R.id.tripImage)

            val imagePair = Pair.create(tripImage as View, "tImage")

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@TripsListActivity,
                    imagePair)
			startActivity(DaysListActivity.newIntent(this@TripsListActivity, trip), options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

		linearLayoutManager = LinearLayoutManager(this)
        tripList.layoutManager = linearLayoutManager

		swipeRefreshLayout.setOnRefreshListener(this)

        loadTrips() { trips ->
			setupAdapter(trips)
		}
    }

	override fun onRefresh() {
		loadTrips() { trips ->
			swipeRefreshLayout.isRefreshing = false
			adapter.trips = trips
			adapter.notifyDataSetChanged()
		}
	}

    fun setupAdapter(trips: List<Trip>) {
        adapter = TripsListAdapter(this, trips)
        tripList.adapter = adapter
        adapter.setOnItemClickListener(onItemClickListener)
    }

	fun startLoadIndicators() {
		tripListProgress.visibility = View.VISIBLE
		tripList.visibility = View.GONE
	}

	fun stopLoadIndicators() {
		tripListProgress.visibility = View.GONE
		tripList.visibility = View.VISIBLE
	}

	fun activeTrips(allTrips: List<Trip>): List<Trip> {
		return allTrips.filter { !it.IsArchived }
	}

	fun handleLoadError() {
		val alertBuilder = AlertDialog.Builder(this)
		alertBuilder.setMessage(getString(R.string.loadingTripsErrorMessage))
				.setPositiveButton(getString(R.string.loadingErrorOK), null)
				.create()
				.show()

	}

    fun loadTrips(handler: (List<Trip>) -> Unit) {
		startLoadIndicators()
        TravefyAPI.getTrips { request, response, result ->
            when(result) {
                is Result.Success -> {
                    stopLoadIndicators()

					handler(activeTrips(result.value))
                }
                is Result.Failure -> {
					stopLoadIndicators()
					swipeRefreshLayout.isRefreshing = false

					handleLoadError()
                }
            }
        }
    }
}
