package com.team1.projectteam1.domain.data


import com.team1.projectteam1.presentation.home.post.PostModel
import com.team1.projectteam1.presentation.home.post.PostResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PostRetroInterface {
    @Headers("accept: application/json",
        "content-type: application/json")
    @POST("/feeds/feed")
    fun post_posting(
        @Body jsonParams: PostModel
        ): Call<PostResult>
}
