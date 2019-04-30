package com.jml.random.users.users.data.repository

import com.jml.random.users.common.extensions.subscribeOnIO
import com.jml.random.users.users.data.datasource.UserDAODataSource
import com.jml.random.users.users.data.datasource.UserRemoteDataSource
import com.jml.random.users.users.data.mapper.UsersMapper
import com.jml.random.users.users.domain.model.User
import io.reactivex.Single

class UserRepositoryImpl(
    private val usersRemoteDS: UserRemoteDataSource,
    private val userDataBaseDS: UserDAODataSource
) : UserRepository {


    override fun getUsers(): Single<List<User>> {
        return usersRemoteDS.requestUsers(0, 40)
            .subscribeOnIO()
            .map { it.results }
            .map(UsersMapper::mapFromUserResponseToModel)
    }


}