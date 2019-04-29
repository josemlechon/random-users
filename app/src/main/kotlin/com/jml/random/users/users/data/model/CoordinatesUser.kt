package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName

data class CoordinatesUser(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)
