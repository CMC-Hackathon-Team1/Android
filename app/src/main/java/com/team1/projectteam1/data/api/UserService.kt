package com.team1.projectteam1.data.api

import com.team1.projectteam1.data.model.StatisticsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users/{profileId}/profile/statistics")
    suspend fun getStatistics(
        @Path("profileId") profileId: Int
    ): Response<StatisticsResponse>
}