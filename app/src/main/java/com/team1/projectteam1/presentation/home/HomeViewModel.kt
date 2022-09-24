package com.team1.projectteam1.presentation.home

import androidx.lifecycle.ViewModel
import com.team1.projectteam1.domain.model.Calendar
import com.team1.projectteam1.util.printLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    var currentYear: Int = 2022
    var currentMonth: Int = 9
    var dayCount = 0
    var startDay = 0
    var preMonthDayCount = 0

    private val _calendarDataListFlow = MutableStateFlow<List<Calendar>>(emptyList())
    val calendarDataListFlow: StateFlow<List<Calendar>> = _calendarDataListFlow

    fun getDateString() = "${currentYear}년 ${currentMonth}월"

    fun setCalendar() {
        var preDayCount = if (startDay == 7) 0 else startDay
        var nextDayCount = 42 - preDayCount - dayCount
        printLog("pre : $preDayCount, current : $dayCount, next : $nextDayCount")

        val calendarList = mutableListOf<Calendar>()

        (1..dayCount).forEach {
            calendarList.add(
                Calendar(it, false, true)
            )
        }

        (1..preDayCount).forEach {
            calendarList.add(0, Calendar(preMonthDayCount - it + 1, false, false))
        }

        (1..nextDayCount).forEach {
            calendarList.add(Calendar(it, false, false))
        }

        _calendarDataListFlow.value = calendarList
    }
}