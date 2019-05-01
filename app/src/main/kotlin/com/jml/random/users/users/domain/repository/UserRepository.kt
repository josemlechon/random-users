package com.jml.random.users.users.domain.repository

import com.jml.random.users.users.domain.model.User
import io.reactivex.Maybe
import io.reactivex.Single

interface UserRepository {

    fun getUsers(): Single<List<User>>

    fun getFilterUsers(filter: String): Maybe<List<User>>

    fun getLocalUsers(): Maybe<List<User>>

    fun getUser(id: String): Maybe<User>

}