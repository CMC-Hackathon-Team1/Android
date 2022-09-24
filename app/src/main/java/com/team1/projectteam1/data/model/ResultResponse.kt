package com.team1.projectteam1.data.model

import com.team1.projectteam1.domain.model.Statistics

data class ResultResponse(
    val feedCount: String,
    val followCount: String,
    val likeCount: String
) {
    fun mapToStatistics() = Statistics(
        likeCount, feedCount, followCount
    )
}