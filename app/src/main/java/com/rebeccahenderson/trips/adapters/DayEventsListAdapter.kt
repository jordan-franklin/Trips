package com.rebeccahenderson.trips.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rebeccahenderson.trips.DayEventsData
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.models.TripEvent
import kotlinx.android.synthetic.main.row_dayevent.view.*

/**
 * Created by becky on 3/7/18.
 */
class DayEventsListAdapter(private var context: Context, val events: List<TripEvent>) : RecyclerView.Adapter<DayEventsListAdapter.ViewHolder>() {

    override fun getItemCount() = events.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_dayevent, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val event = events[position]
        holder?.itemView?.eventName?.text = event.Name

        val mimeType = "text/html"
        val encoding = "UTF-8";
        holder?.itemView?.eventDescription?.loadDataWithBaseURL("", event.Description, mimeType, encoding, "")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}