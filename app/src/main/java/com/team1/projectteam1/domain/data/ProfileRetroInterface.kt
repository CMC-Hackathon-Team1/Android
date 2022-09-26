package com.team1.projectteam1.domain.data

import com.team1.projectteam1.presentation.home.profile.CreateProfileResponse
import retrofit2.http.POST

interface ProfileRetroInterface {

    @POST("users/create")
    fun createProfile(
    ): retrofit2.Call<CreateProfileResponse>
}