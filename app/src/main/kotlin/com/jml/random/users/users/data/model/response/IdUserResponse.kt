package com.jml.random.users.users.data.model.response

import com.google.gson.annotations.SerializedName


data class IdUserResponse(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)