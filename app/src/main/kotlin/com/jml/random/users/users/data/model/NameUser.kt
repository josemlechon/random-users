package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName

data class NameUser(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String,
    @SerializedName("title") val title: String
)