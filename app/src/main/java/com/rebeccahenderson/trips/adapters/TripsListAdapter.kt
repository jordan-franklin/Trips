package com.rebeccahenderson.trips.adapters

import android.content.Context
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.florent37.picassopalette.PicassoPalette
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.models.Trip
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_trip.view.*

/**
 * Created by becky on 3/6/18.
 */

class TripsListAdapter(private var context: Context, var trips: List<Trip>) : RecyclerView.Adapter<TripsListAdapter.ViewHolder>() {

	lateinit var itemClickListener: OnItemClickListener

	override fun getItemCount() = trips.size

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_trip, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		val trip = trips[position]
		holder?.itemView?.tripName?.text = trip.Name
		Picasso.with(context)
				.load(trip.TripCoverPhotoUrl)
				.into(holder?.itemView?.tripImage,
						PicassoPalette.with(trip.TripCoverPhotoUrl.toString(), holder?.itemView?.tripImage)
								.use(PicassoPalette.Profile.MUTED_DARK)
								.intoBackground(holder?.itemView?.tripNameHolder)
				);
	}

	fun updateTrips(trips: List<Trip>) {

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
