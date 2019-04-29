package com.jml.random.users.users.data.model.response

import com.google.gson.annotations.SerializedName

data class NameUserResponse(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String,
    @SerializedName("title") val title: String
)