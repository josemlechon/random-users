package com.jml.random.users.users.data.datasource

import com.jml.random.users.network.handler.NetworkErrorHandler
import com.jml.random.users.network.model.BaseResponse
import com.jml.random.users.users.data.model.response.UserResponse
import io.reactivex.Single

class UserRemoteDataSource constructor(
    private val usersApi: RandomUsersApi
) {

    fun requestUsers(page: Int, results: Int): Single<BaseResponse<List<UserResponse>>> {

        return usersApi.getUsers(
            page = page,
            results = results
        )
            .compose(NetworkErrorHandler.parseSingleHttpErrors())
    }


}