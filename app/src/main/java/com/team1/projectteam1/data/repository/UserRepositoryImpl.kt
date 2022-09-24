package com.team1.projectteam1.data.repository

import com.team1.projectteam1.data.api.UserService
import com.team1.projectteam1.data.model.StatisticsResponse
import com.team1.projectteam1.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun getUserStatistics(): Response<StatisticsResponse> {
       return userService.getStatistics(1)
    }
}