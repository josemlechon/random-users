package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName


data class IdUser(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)