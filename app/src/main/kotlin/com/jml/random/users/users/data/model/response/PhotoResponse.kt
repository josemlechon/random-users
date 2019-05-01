package com.jml.random.users.users.data.model.response

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("large") val large: String?,
    @SerializedName("medium") val medium: String?,
    @SerializedName("picture") val thumbnail: String?
)