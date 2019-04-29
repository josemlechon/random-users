package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName

data class RegisteredUser(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)


