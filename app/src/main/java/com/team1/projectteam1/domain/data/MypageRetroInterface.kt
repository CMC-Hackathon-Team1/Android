package com.team1.projectteam1.domain.data

import com.team1.projectteam1.presentation.mypage.GetMypageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MypageRetroInterface {

    @GET("/users/{profileId}")
    fun getMyPage(
        @Path("profileId") profileId : Int
    ) : retrofit2.Call<GetMypageResponse>
}