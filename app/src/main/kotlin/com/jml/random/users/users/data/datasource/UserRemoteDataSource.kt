package com.jml.random.users.users.data.datasource

import com.jml.random.users.network.handler.NetworkErrorHandler
import com.jml.random.users.network.model.BaseResponse
import com.jml.random.users.users.data.model.response.UserResponse
import io.reactivex.Single

class UserRemoteDataSource constructor(
    private val usersApi: RandomUsersApi
) {

    companion object {
        private const val NUM_ITEMS_PAGE = 10
    }

    fun requestInitialUsers(): Single<BaseResponse<List<UserResponse>>> {
        return usersApi.getUsers(
            results = NUM_ITEMS_PAGE
        )
            .compose(NetworkErrorHandler.parseSingleHttpErrors())
    }

    fun requestUsers(page: Int, results: Int, seed: String): Single<BaseResponse<List<UserResponse>>> {
        return usersApi.getUsers(
            page = page,
            results = results,
            seed = seed
        )
            .compose(NetworkErrorHandler.parseSingleHttpErrors())
    }
}