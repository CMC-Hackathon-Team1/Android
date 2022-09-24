package com.team1.projectteam1.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team1.projectteam1.domain.model.Calendar
import com.team1.projectteam1.domain.model.MyPostHome
import com.team1.projectteam1.domain.model.MyProfile
import com.team1.projectteam1.domain.model.RelevantUser
import com.team1.projectteam1.domain.repository.UserRepository
import com.team1.projectteam1.util.printLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var currentYear: Int = 2022
    var currentMonth: Int = 9
    var dayCount = 0
    var startDay = 0
    var preMonthDayCount = 0

    private var dayList = listOf("토", "금", "목", "수", "화", "월", "일")

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
                Calendar(it.toString(), false, true)
            )
        }

        (1..preDayCount).forEach {
            calendarList.add(0, Calendar((preMonthDayCount - it + 1).toString(), false, false))
        }

        (1..nextDayCount).forEach {
            calendarList.add(Calendar(it.toString(), false, false))
        }

        dayList.forEach {
            calendarList.add(0, Calendar(it, false, true))
        }

        _calendarDataListFlow.value = calendarList
    }


    // dummy data

    fun getMyProfileDummy(): List<MyProfile> {
        return listOf(
            MyProfile("", "작가", false),
            MyProfile("", "디자이너", false),
            MyProfile("", "", true),
        )
    }

    fun getMyPostHomeDummy(): List<MyPostHome> {
        return listOf(
            MyPostHome("\uD83D\uDC9E", "#에세이", 10),
            MyPostHome("\uD83D\uDCDD", "#에세이", 11),
            MyPostHome("\uD83D\uDC65", "#에세이", 12),
        )
    }

    fun getRelevantUserDummy(): List<RelevantUser> {
        return listOf(
            RelevantUser("", "지니"),
            RelevantUser("", "안나"),
            RelevantUser("", "준1"),
            RelevantUser("", "샐리"),
            RelevantUser("", "준2"),
            RelevantUser("", "준3"),
        )
    }

    //

    fun getStatistics() {
        viewModelScope.launch {
            val result = userRepository.getUserStatistics()
            if(result.isSuccessful) {
                printLog("통계 조회 성공")
            } else {
                printLog("통계 조회 실패")
            }
        }
    }
}