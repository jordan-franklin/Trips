package com.rebeccahenderson.trips.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rebeccahenderson.trips.R
import com.rebeccahenderson.trips.models.Day
import com.rebeccahenderson.trips.models.Trip
import kotlinx.android.synthetic.main.row_tripdate.view.*
import java.text.SimpleDateFormat

/**
 * Created by becky on 3/7/18.
 */

class DaysListAdapter(private var context: Context, val days: List<Day>, val trip: Trip) : RecyclerView.Adapter<DaysListAdapter.ViewHolder>() {

    lateinit var itemClickListener: OnItemClickListener

    override fun getItemCount() = days.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_tripdate, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val tripDay = days[position]
        holder?.itemView?.tripDayName?.text = tripDay.Title ?: SimpleDateFormat("EEEE").format(tripDay.Date)
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