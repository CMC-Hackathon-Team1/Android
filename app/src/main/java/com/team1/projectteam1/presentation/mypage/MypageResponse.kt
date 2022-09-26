package com.team1.projectteam1.presentation.mypage

import com.google.gson.annotations.SerializedName

data class GetMypageResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : ArrayList<GetMypageResult>
)

data class GetMypageResult(
    @SerializedName("feedId") val feedId : Int,
    @SerializedName("profileId") val profileId : Int,
    @SerializedName("likeCount") val likeCount : String,
    @SerializedName("userId") val userId : Int,
    @SerializedName("personaId") val personaId : Int,
    @SerializedName("profileImgUrl") val profileImgUrl : String,
    @SerializedName("statusMessage") val statusMessage : String,
    @SerializedName("profileName") val profileName : String,
    @SerializedName("personaName") val personaName : String,
    @SerializedName("content") val content : String,
    @SerializedName("isLike") val isLike : Int,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("hashTagStr") val hashTagStr : String,



)