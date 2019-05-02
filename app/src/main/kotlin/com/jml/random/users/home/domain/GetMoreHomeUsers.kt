package com.jml.random.users.home.domain

import com.jml.random.users.home.view.mapper.UserUIMapper
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.Single

class GetMoreHomeUsers constructor(
    private val usersRepo: UserRepository
) {

    fun execute(): Maybe<List<UserBriefUI>> {
        return usersRepo.getUsers()
            .filter { it.isNotEmpty() }
            .map(UserUIMapper::mapFromUserToUserUI)
    }
}