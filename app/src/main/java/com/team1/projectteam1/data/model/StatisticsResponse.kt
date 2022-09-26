package com.team1.projectteam1.data.model

data class StatisticsResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ResultResponse
)