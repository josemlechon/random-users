package com.jml.random.users.users.data.mapper

import com.jml.random.users.users.data.model.db.UserEntity
import com.jml.random.users.users.data.model.db.UserLocationEntity
import com.jml.random.users.users.data.model.db.UserNameEntity
import com.jml.random.users.users.data.model.db.UserPhotoEntity
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
            large = res.large,
            medium = res.medium,
            thumbnail = res.thumbnail
        )
    }


    fun mapFromUserResponseToModel(response: List<UserResponse>): List<User> {

        return response.map {
            User(
                id = it.login.uuid,
                titleName = it.name.title,
                firstName = it.name.first,
                lastName = it.name.last,
                email = it.email,
                phone = it.phone,
                pictures = mapFromPhotoResponseToModel(it.picture)
            )
        }
    }


    private fun mapFromPhotoResponseToModel(res: PhotoResponse): UserPhotos {
        return UserPhotos(
            large = res.large,
            medium = res.medium,
            thumbnail = res.thumbnail
        )
    }
}