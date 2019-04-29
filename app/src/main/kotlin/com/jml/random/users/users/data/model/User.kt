package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: IdUser,
    @SerializedName("cell") val cell: String,
    @SerializedName("dob") val dob: DobUser,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("location") val location: LocationUser,
    @SerializedName("login") val login: LoginInfoUser,
    @SerializedName("name") val name: NameUser,
    @SerializedName("nat") val nat: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture") val picture: PictureUser,
    @SerializedName("registered") val registered: RegisteredUser
)
