package com.rebeccahenderson.trips

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_dayevent.view.*

/**
 * Created by becky on 3/7/18.
 */
class DayEventsListAdapter(private var context: Context) : RecyclerView.Adapter<DayEventsListAdapter.ViewHolder>() {

    override fun getItemCount() = DayEventsData.dayEventsList().size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_dayevent, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val event = DayEventsData.dayEventsList()[position]
        holder?.itemView?.eventName?.text = event

        val mimeType = "text/html"
        val encoding = "UTF-8";
        holder?.itemView?.eventDescription?.loadDataWithBaseURL("", DayEventsData.eventDescriptionHTML, mimeType, encoding, "")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}