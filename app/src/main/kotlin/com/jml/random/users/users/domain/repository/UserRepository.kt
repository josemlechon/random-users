package com.jml.random.users.users.domain.repository

import com.jml.random.users.users.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserRepository {


    fun getUsers(): Single<List<User>>

    fun getLocalUsers(): Maybe<List<User>>

    fun getMoreUsers(): Maybe<List<User>>

    fun getUser(id: String): Maybe<User>

}