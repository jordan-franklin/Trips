package com.rebeccahenderson.trips.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.models.Event
import kotlinx.android.synthetic.main.row_event.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by becky on 3/7/18.
 */
class EventsListAdapter(private var context: Context,
						val events: List<Event>,
						val date: Date) : RecyclerView.Adapter<EventsListAdapter.ViewHolder>() {

    override fun getItemCount() = events.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_event, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val event = events[position]
        holder?.itemView?.eventName?.text = event.Name
		if (event.StartTimeInMinutes != null) {
			val calendar = Calendar.getInstance()
			calendar.time = date
			calendar.add(Calendar.MINUTE, event.StartTimeInMinutes)
			holder?.itemView?.eventTime?.text = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT).format(calendar.time)
		} else {
			holder?.itemView?.eventTime?.visibility = View.GONE
		}

        val mimeType = "text/html"
        val encoding = "UTF-8";
        holder?.itemView?.eventDescription?.loadDataWithBaseURL("", event.Description, mimeType, encoding, "")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}