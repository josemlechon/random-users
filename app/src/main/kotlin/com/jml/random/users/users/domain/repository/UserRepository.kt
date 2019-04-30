package com.jml.random.users.users.domain.repository

import androidx.paging.PagedList
import com.jml.random.users.users.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {

    fun getUsers() : Single<List<User>>

    fun fetchOrGetUsers(): Flowable<PagedList<User>>

}