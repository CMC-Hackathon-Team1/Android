package com.team1.projectteam1.presentation.home.profile

import com.google.gson.annotations.SerializedName

data class CreateProfileResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : CreateProfileResult
)

data class CreateProfileResult(
    @SerializedName("profiledId") val profileId: Int,

)