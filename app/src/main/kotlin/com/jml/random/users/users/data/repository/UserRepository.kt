package com.jml.random.users.users.data.repository

import com.jml.random.users.users.domain.model.User
import io.reactivex.Single

interface UserRepository {

    fun getUsers() : Single<List<User>>

}