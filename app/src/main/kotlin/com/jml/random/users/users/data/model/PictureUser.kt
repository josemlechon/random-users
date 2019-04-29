package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName

data class PictureUser(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)