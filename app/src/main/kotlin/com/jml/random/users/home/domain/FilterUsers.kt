package com.jml.random.users.home.domain

import com.jml.random.users.home.view.mapper.UserUIMapper
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.Single

class FilterUsers constructor(
    private val usersRepo: UserRepository
) {

    fun execute(filter: String): Maybe<List<UserBriefUI>> {
        return usersRepo.getFilterUsers(filter)
            .filter { it.isNotEmpty() }
            .map(UserUIMapper::mapFromUserToUserUI)
            .onErrorReturn { listOf() }
    }
}