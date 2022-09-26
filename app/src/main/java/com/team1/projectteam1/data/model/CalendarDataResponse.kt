package com.team1.projectteam1.data.model

data class CalendarDataResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<CalendarResult>
)