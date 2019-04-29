package com.jml.random.users.users.domain.usecases

import com.jml.random.users.users.data.repository.UserRepository

class GetUser constructor(
    private val usersRepo: UserRepository
) {

    fun execute() {

    }
}