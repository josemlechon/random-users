package com.jml.random.users.users.data.datasource

import com.jml.random.users.network.model.BaseResponse
import com.jml.random.users.users.data.model.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersApi {

    @GET("/api/")
    fun getUsers(
        @Query("page") page: Int=0,
        @Query("results") results: Int,
        @Query("seed") seed: String=""
    )
            : Single<BaseResponse<List<UserResponse>>>
}