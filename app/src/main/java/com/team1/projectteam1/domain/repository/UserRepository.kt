package com.team1.projectteam1.domain.repository

import com.team1.projectteam1.data.model.AllProfileResponse
import com.team1.projectteam1.data.model.StatisticsResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUserStatistics(): Response<StatisticsResponse>
    suspend fun getAllProfile(): Response<AllProfileResponse>
}