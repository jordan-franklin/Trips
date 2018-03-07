package com.rebeccahenderson.trips

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_trips.*

class Trips : AppCompatActivity() {

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TripsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        tripList.layoutManager = staggeredLayoutManager

        adapter = TripsListAdapter(this)
        tripList.adapter = adapter
    }
}
