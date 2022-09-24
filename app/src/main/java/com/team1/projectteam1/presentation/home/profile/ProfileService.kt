package com.team1.projectteam1.presentation.home.profile

import android.util.Log
import com.team1.projectteam1.domain.data.ProfileRetroInterface
import com.team1.projectteam1.domain.model.ApiClient.getRetrofit
import retrofit2.Call
import retrofit2.Response

class ProfileService {
    private lateinit var profileView: ProfileView

    fun setProfileView(profileView: ProfileView) {
        this.profileView = profileView
    }

    fun createProfileView() {
        val profileService = getRetrofit().create(ProfileRetroInterface::class.java)

        profileService.createProfile().enqueue(object : retrofit2.Callback<CreateProfileResponse> {
            override fun onResponse(
                call: Call<CreateProfileResponse>,
                response: Response<CreateProfileResponse>
            ) {
                Log.d("CreateProfile/SUCCESS", response.toString())
                val resp : CreateProfileResponse = response.body()!!

                when (val code=resp.code) {
                    1000 -> profileView.onInputProfileSuccess(code, resp.result!!)
                }
            }

            override fun onFailure(call: Call<CreateProfileResponse>, t: Throwable) {
               Log.d("CreateProfile/FAILURE", t.message.toString())
            }
        })
        Log.d("CreateProfile", "finish")
    }
}