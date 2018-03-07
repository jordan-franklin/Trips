package com.rebeccahenderson.trips

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_trip.view.*

/**
 * Created by becky on 3/6/18.
 */

class TripsListAdapter(private var context: Context) : RecyclerView.Adapter<TripsListAdapter.ViewHolder>() {

	lateinit var itemClickListener: OnItemClickListener

	override fun getItemCount() = TripsData.tripsList().size

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_trip, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		val trip = TripsData.tripsList()[position]
		holder?.itemView?.tripName?.text = trip
//		Picasso.with(context).load(place.getImageResourceId(context)).into(holder?.itemView?.placeImage)
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
		init {
			itemView.tripHolder.setOnClickListener(this)
		}

		override fun onClick(view: View) = itemClickListener.onItemClick(itemView, adapterPosition)
	}

	interface OnItemClickListener {
		fun onItemClick(view: View, position: Int)
	}

	fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
		this.itemClickListener = itemClickListener
	}
}
