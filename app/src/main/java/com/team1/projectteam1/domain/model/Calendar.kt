package com.team1.projectteam1.domain.model

data class Calendar(
    val day: Int,
    val isExist: Boolean = false,
    val isCurrentMonth: Boolean = false
)
