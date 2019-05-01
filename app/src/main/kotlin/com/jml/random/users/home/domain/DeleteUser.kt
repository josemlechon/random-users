package com.jml.random.users.home.domain

import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Completable

class DeleteUser constructor(
    private val usersRepo: UserRepository
) {

    fun execute(id: String): Completable {
        return usersRepo.deleteUser(id)
    }
}