package com.team1.projectteam1.presentation.home.post
import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("category") val category : String,
    @SerializedName("hashtag") val hashtag : String?=null,
    @SerializedName("content") val content : String,
    @SerializedName("status") val status : String,
    @SerializedName("imgUrl") val imgUrl : String?=null,
)
data class PostResult(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
)