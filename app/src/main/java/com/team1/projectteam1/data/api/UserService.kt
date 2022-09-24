package com.team1.projectteam1.data.api

import com.team1.projectteam1.presentation.mypage.GetMypageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{profileId}")
    fun getStatistics(
        @Path("profileId") profileId : Int
    ) : retrofit2.Call<GetMypageResponse>
}