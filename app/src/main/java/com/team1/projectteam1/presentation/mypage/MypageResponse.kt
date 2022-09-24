package com.team1.projectteam1.presentation.mypage

import com.google.gson.annotations.SerializedName

data class GetMypageResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : GetMypageResult
)

data class GetMypageResult(
    @SerializedName("idx") val idx : Int,
    @SerializedName("lat") val lat : Double,
)