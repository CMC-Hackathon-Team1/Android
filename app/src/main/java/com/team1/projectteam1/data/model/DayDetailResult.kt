package com.team1.projectteam1.data.model

import com.team1.projectteam1.domain.model.DayDetail

data class DayDetailResult(
    val content: String,
    val createdAt: String,
    val feedId: Int,
    val hashTagStr: String?,
    val likeCount: String,
    val personaId: Int,
    val personaName: String,
    val profileId: Int,
    val profileImgUrl: String,
    val profileName: String,
    val statusMessage: String,
    val userId: Int,
    val ImgUrl: String
) {
    fun mapToDayDetail() = DayDetail(
        content = content,
        createdAt = createdAt,
        feedId = feedId,
        hashTagStr = hashTagStr,
        likeCount = likeCount,
        personaId= personaId,
        personaName = personaName,
        profileId = profileId,
        profileImgUrl = profileImgUrl,
        profileName = profileName,
        statusMessage = statusMessage,
        userId = userId,
        ImgUrl = ImgUrl
    )
}