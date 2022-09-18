package com.kys.caerangex_2022_prac1.calendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kys.caerangex_2022_prac1.R

class CalendarViewHolder(
    itemView: View,
    private var onItemListener: CalendarAdapter.OnItemListener
) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    var dayOfMonth: TextView

    init {
        dayOfMonth = itemView.findViewById(R.id.cellDayText)
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onItemListener.onItemClick(adapterPosition, dayOfMonth.text.toString())
    }

}