package com.team1.projectteam1.domain.repository

import com.team1.projectteam1.data.model.AllProfileResponse
import com.team1.projectteam1.data.model.CalendarDataResponse
import com.team1.projectteam1.data.model.DayDetailResponse
import com.team1.projectteam1.data.model.StatisticsResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUserStatistics(): Response<StatisticsResponse>
    suspend fun getAllProfile(): Response<AllProfileResponse>
    suspend fun getCalendarData(year: String, month: String): Response<CalendarDataResponse>
    suspend fun getDayDetials(year: String, month: String, day: String): Response<DayDetailResponse>
}