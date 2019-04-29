package com.jml.random.users.users.data.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: IdUserResponse,
    @SerializedName("cell") val cell: String,
    @SerializedName("dob") val dob: DateResponse,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("location") val location: LocationUserResponse,
    @SerializedName("login") val login: LoginInfoUser,
    @SerializedName("name") val name: NameUserResponse,
    @SerializedName("nat") val nat: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture") val picture: PhotoResponse,
    @SerializedName("registered") val registered: DateResponse
)
