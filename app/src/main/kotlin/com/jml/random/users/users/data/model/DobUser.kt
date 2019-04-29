package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName

data class DobUser(
    @SerializedName("age") val age: Int,
    @SerializedName("date") val date: String
)
