package com.rebeccahenderson.trips

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_trips.*

class Trips : AppCompatActivity() {

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TripsListAdapter

    private val onItemClickListener = object : TripsListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
//            Toast.makeText(this@MainActivity, "Clicked " + position, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        tripList.layoutManager = staggeredLayoutManager

        adapter = TripsListAdapter(this)
        tripList.adapter = adapter
        adapter.setOnItemClickListener(onItemClickListener)
    }
}
