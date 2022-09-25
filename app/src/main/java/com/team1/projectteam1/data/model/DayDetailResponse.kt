package com.team1.projectteam1.data.model

data class DayDetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<DayDetailResult>
)