package com.jml.random.users.users.domain.model

data class User(
    val id: String,
    val titleName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val pictures: UserPhotos
) {

    fun getFullName(): String = "$firstName $lastName"
}