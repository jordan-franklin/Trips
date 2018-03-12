package com.rebeccahenderson.trips.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.models.Day
import com.rebeccahenderson.trips.models.Trip
import kotlinx.android.synthetic.main.row_day.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created by becky on 3/7/18.
 */

class DaysListAdapter(private var context: Context, val days: List<Day>, val trip: Trip) : RecyclerView.Adapter<DaysListAdapter.ViewHolder>() {

    lateinit var itemClickListener: OnItemClickListener

    override fun getItemCount() = days.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_day, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		fun setDayName(day: Day) {
			if (day.Title != null) {
				holder?.itemView?.tripDayName?.text = day.Title
			} else if (day.Date != null) {
				holder?.itemView?.tripDayName?.text = SimpleDateFormat("EEEE").format(day.Date)
			} else {
				holder?.itemView?.tripDayName?.text = "${context.getString(R.string.day)} ${position+1}"
			}
		}

		fun setDate(day: Day) {
			if (day.Date != null) {
				holder?.itemView?.date?.text = DateFormat.getDateInstance().format(day.Date)
			}
		}

		fun setEventsInfo(day: Day) {
			val events = day.TripEvents
			val numEvents = if (events != null) events.count() else 0
			if (numEvents == 0) {
				holder?.itemView?.eventCount?.text = context.getString(R.string.noEvents)
				holder?.itemView?.tripDayCard?.setCardBackgroundColor(Color.LTGRAY)
				holder?.itemView?.tripDayHolder?.background = null
			} else {
				holder?.itemView?.eventCount?.text = numEvents.toString() + " " + (if (numEvents == 1) context.getString(R.string.event) else context.getString(R.string.events))
			}
		}

		val tripDay = days[position]
		setDayName(tripDay)
		setDate(tripDay)
		setEventsInfo(tripDay)
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