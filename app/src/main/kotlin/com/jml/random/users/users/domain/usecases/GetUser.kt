package com.jml.random.users.users.domain.usecases

import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Single

class GetUser constructor(
    private val userRepo: UserRepository
) {

    fun execute(id: String): Single<User> {
        return userRepo.getUser(id)
            .toSingle()
    }
}