package com.team1.projectteam1.domain.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.BitSet

object ApiClient {
    private const val BASE_URL = "baseurl"

    fun getRetrofit() : Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}