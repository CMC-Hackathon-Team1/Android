package com.team1.projectteam1.data.repository

import com.team1.projectteam1.data.api.UserService
import com.team1.projectteam1.data.model.AllProfileResponse
import com.team1.projectteam1.data.model.CalendarDataResponse
import com.team1.projectteam1.data.model.DayDetailResponse
import com.team1.projectteam1.data.model.StatisticsResponse
import com.team1.projectteam1.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun getUserStatistics(): Response<StatisticsResponse> {
       return userService.getStatistics(1)
    }

    override suspend fun getAllProfile(): Response<AllProfileResponse> {
        return userService.getAllProfile(1)
    }

    override suspend fun getCalendarData(year: String, month: String): Response<CalendarDataResponse> {
        return userService.getCalendarData(year, month)
    }

    override suspend fun getDayDetials(
        year: String,
        month: String,
        day: String
    ): Response<DayDetailResponse> {
        return userService.getDayDetails(1, year, month, day)
    }
}