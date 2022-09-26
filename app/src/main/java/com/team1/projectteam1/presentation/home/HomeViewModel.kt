package com.team1.projectteam1.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team1.projectteam1.domain.model.*
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

    var selectedDay = "1"

    private var dayList = listOf("토", "금", "목", "수", "화", "월", "일")

    private val _calendarDataListFlow = MutableStateFlow<List<Calendar>>(emptyList())
    val calendarDataListFlow: StateFlow<List<Calendar>> = _calendarDataListFlow

    private val _statisticsFlow = MutableStateFlow<Statistics>(Statistics())
    val statisticsFlow: StateFlow<Statistics> = _statisticsFlow

    private val _myProfileListFlow = MutableStateFlow<List<MyProfile>>(emptyList())
    val myProfileListFlow: StateFlow<List<MyProfile>> = _myProfileListFlow

    private val _dayDetailListFlow = MutableStateFlow<List<DayDetail>>(emptyList())
    val dayDetailListFlow: StateFlow<List<DayDetail>> = _dayDetailListFlow

    var map = mutableMapOf<Int, String>()

    fun getDateString() = "${currentYear}년 ${currentMonth}월"

    fun getCalendarData() {
        viewModelScope.launch {
            val month = if (currentMonth <= 9) "0${currentMonth}" else "$currentMonth"
            val result = userRepository.getCalendarData(currentYear.toString(), month)
            map.clear()
            if (result.isSuccessful) {
                printLog("달력 데이터 조회 성공")
                result.body()!!.result.forEach {
                    val date = it.date.substring(8, 10).toInt()
                    map[date] = it.ImgUrl
                }
                printLog("${map}")
                setCalendar()
            } else {
                printLog("달력 데이터 조회 실패")
            }
        }
    }

    fun setCalendar() {
        var preDayCount = if (startDay == 7) 0 else startDay
        var nextDayCount = 42 - preDayCount - dayCount
        printLog("pre : $preDayCount, current : $dayCount, next : $nextDayCount")

        val calendarList = mutableListOf<Calendar>()

        (1..dayCount).forEach {
            if (map[it] != null) {
                calendarList.add(
                    Calendar(it.toString(), true, true, map[it]!!)
                )
            } else {
                calendarList.add(
                    Calendar(it.toString(), false, true)
                )
            }
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

    fun getMyPostHome(statistics: Statistics): List<MyPostHome> {
        return listOf(
            MyPostHome("\uD83D\uDC9E", "이번달에 ${statistics.likeCount}개의 공감을 받았어요!", 10),
            MyPostHome("\uD83D\uDCDD", "이번달에 ${statistics.feedCount}개의 글을 작성했어요!", 11),
            MyPostHome("\uD83D\uDC65", "이번달에 ${statistics.followCount}명 팔로우를 했어요!", 12),
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
            if (result.isSuccessful) {
                printLog("통계 조회 성공")
                if (result.body() == null) printLog("body null")
                else printLog("body not null")

                _statisticsFlow.value = result.body()!!.result.mapToStatistics()
            } else {
                printLog("통계 조회 실패")
            }
        }
    }

    fun getAllProfile() {
        viewModelScope.launch {
            val result = userRepository.getAllProfile()
            if (result.isSuccessful) {
                printLog("모든 페르소나 프로필 조회 성공")
                val myProfileList = ArrayList<MyProfile>()
                val profileList = result.body()!!.result
                myProfileList.addAll(profileList.map {
                    MyProfile(it.profileImgUrl, it.profileName, false)
                })
                myProfileList.add(MyProfile("", "", true))
                _myProfileListFlow.value = myProfileList
            } else {
                printLog("모든 페르소나 프로필 조회 실패")
            }
        }
    }

    fun getDayDetails() {
        viewModelScope.launch {
            val month = if (currentMonth <= 9) "0${currentMonth}" else "$currentMonth"
            val day = if(selectedDay.toInt() <= 9) "0${selectedDay}" else "$selectedDay"
            val result = userRepository.getDayDetials(
                currentYear.toString(),
                month,
                day
            )
            printLog("daydetail : ${currentYear} ${month} ${day}")
            if (result.isSuccessful) {
                printLog("일 상세 데이터 리스트 조회 성공")
                val dayDetailsList = result.body()!!.result.map {
                    it.mapToDayDetail()
                }
                _dayDetailListFlow.value = dayDetailsList
            } else {
                printLog("일 상세 데이터 리스트 조회 실패")
            }
        }
    }
}