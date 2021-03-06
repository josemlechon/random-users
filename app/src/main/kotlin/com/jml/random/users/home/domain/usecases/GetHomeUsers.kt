package com.jml.random.users.home.domain.usecases

import com.jml.random.users.home.view.mapper.UserUIMapper
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Single

class GetHomeUsers constructor(
    private val usersRepo: UserRepository
) {

    fun execute(): Single<List<UserBriefUI>> {

        return usersRepo.getLocalUsers()
            .filter { it.isNotEmpty() }
            .switchIfEmpty(usersRepo.getUsers())
            .map(UserUIMapper()::mapFromUserToUserUI)
    }
}