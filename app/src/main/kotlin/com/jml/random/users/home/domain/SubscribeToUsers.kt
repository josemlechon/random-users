package com.jml.random.users.home.domain

import androidx.paging.PagedList
import com.jml.random.users.home.view.mapper.UserUIMapper
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single

class SubscribeToUsers constructor(
    private val usersRepo: UserRepository
) {

    fun execute(): Flowable<PagedList<User>> {
       return usersRepo.fetchOrGetUsers()
           // .map(UserUIMapper::mapFromUserToUserUI)
    }
}