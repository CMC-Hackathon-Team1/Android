package com.team1.projectteam1.domain.model

data class Calendar(
    val day: String,
    val isExist: Boolean = false,
    val isCurrentMonth: Boolean = false,
    val imageUrl: String = ""
)
