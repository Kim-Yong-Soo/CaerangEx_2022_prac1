package com.kys.caerangex_2022_prac1.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kys.caerangex_2022_prac1.R
import com.kys.caerangex_2022_prac1.calendar.CalendarAdapter.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

class CalendarActivity : Fragment(), OnItemListener {

    private lateinit var yearMonthText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var selectedDate: LocalDate
    private lateinit var previousMonth: Button
    private lateinit var nextMonth: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_calendar, container, false)
        initWidgets(view)
        selectedDate = LocalDate.now()
        setMonthView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previousMonth.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            setMonthView(view)
        }

        nextMonth.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonthView(view)
        }
    }

    private fun initWidgets(view: View) {
        calendarRecyclerView = view.findViewById(R.id.calendar_rv)
        yearMonthText = view.findViewById(R.id.year_month_tv)
        previousMonth = view.findViewById(R.id.previousMonth)
        nextMonth = view.findViewById(R.id.nextMonth)
    }

    private fun setMonthView(view: View) {
        yearMonthText.text = yearMonthFromDate(selectedDate)

        val daysInMonth: ArrayList<String> = daysInMonthArray(selectedDate)

        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(view.context, 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter

    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray: ArrayList<String> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()

        val firstOfMonth: LocalDate = selectedDate.withDayOfMonth(1)
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value

        for (i: Int in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek)
                daysInMonthArray.add(" ")
            else {
                daysInMonthArray.add((i - dayOfWeek).toString())
                Log.d("daysInMonthArray", (i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun yearMonthFromDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("YYYY년 MM월")
        return date.format(formatter)
    }

    override fun onItemClick(position: Int, dayText: String) {
        if (dayText != " ") {
            val message: String = yearMonthFromDate(selectedDate) + " " + dayText + "일"
            Toast.makeText(view?.context, message, Toast.LENGTH_SHORT).show()
        }
    }
}