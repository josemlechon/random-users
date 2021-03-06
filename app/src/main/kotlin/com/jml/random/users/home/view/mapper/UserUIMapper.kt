package com.jml.random.users.home.view.mapper

import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.users.domain.model.User

class UserUIMapper {

    fun mapFromUserToUserUI(users: List<User>): List<UserBriefUI> {
        return users.map { user ->
            UserBriefUI(
                id = user.id,
                fullName = user.getFullName(),
                email = user.email,
                phone = user.phone,
                picture = user.pictures.medium
            )
        }
    }
}