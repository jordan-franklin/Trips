package com.rebeccahenderson.trips.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.TripDaysData
import kotlinx.android.synthetic.main.row_tripdate.view.*

/**
 * Created by becky on 3/7/18.
 */

class TripDaysListAdapter(private var context: Context) : RecyclerView.Adapter<TripDaysListAdapter.ViewHolder>() {

    lateinit var itemClickListener: OnItemClickListener

    override fun getItemCount() = TripDaysData.tripDaysList().size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_tripdate, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val tripDay = TripDaysData.tripDaysList()[position]
        holder?.itemView?.tripDayName?.text = tripDay
//		Picasso.with(context).load(place.getImageResourceId(context)).into(holder?.itemView?.placeImage)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.tripDayHolder.setOnClickListener(this)
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