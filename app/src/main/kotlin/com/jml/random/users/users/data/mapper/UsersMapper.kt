package com.jml.random.users.users.data.mapper

import com.jml.random.users.users.data.model.db.user.*
import com.jml.random.users.users.data.model.response.*
import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.domain.model.UserPhotos

object UsersMapper {

    fun mapFromUserResponseToEntity(response: List<UserResponse>): List<UserEntity> {

        return response.map {
            UserEntity(
                uuid = it.login.uuid,
                username = it.login.username,
                email = it.email,
                phone = it.phone,
                gender = it.gender,
                location = mapFromLocationResponseToEntity(it.location),
                name = mapFromNameResponseToEntity(it.name),
                photo = mapFromPhotoResponseToEntity(it.picture),
                registered = it.registered.date
            )
        }
    }

    private fun mapFromLocationResponseToEntity(locaRes: LocationUserResponse): UserLocationEntity {
        return UserLocationEntity(
            street = locaRes.street,
            city = locaRes.city,
            state = locaRes.state
        )
    }

    private fun mapFromNameResponseToEntity(res: NameUserResponse): UserNameEntity {
        return UserNameEntity(
            title = res.title,
            first = res.first,
            last = res.last
        )
    }

    private fun mapFromPhotoResponseToEntity(res: PhotoResponse): UserPhotoEntity {
        return UserPhotoEntity(
            large = res.large ?: "",
            medium = res.medium ?: "",
            thumbnail = res.thumbnail ?: ""
        )
    }

    fun mapFromUsersEntityToModel(entity: List<UserEntity>): List<User> {
        return entity.map(::mapFromUserEntityToModel)
    }

    fun mapFromUserEntityToModel(entity: UserEntity): User {

        return User(
            id = entity.uuid,
            titleName = entity.name.title,
            firstName = entity.name.first,
            lastName = entity.name.last,
            email = entity.email,
            phone = entity.phone,
            pictures = mapFromPhotoEntityToModel(entity.photo)
        )
    }


    private fun mapFromPhotoEntityToModel(entity: UserPhotoEntity): UserPhotos {
        return UserPhotos(
            large = entity.large,
            medium = entity.medium,
            thumbnail = entity.thumbnail
        )
    }

     fun mapFromUserEntityToDeleteEntity(entity: UserEntity): DeletedUserEntity {
        return DeletedUserEntity(
            uuid = entity.uuid,
            username = entity.username
        )
    }
}