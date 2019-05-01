package com.jml.random.users.users.data.model.response

import com.google.gson.annotations.SerializedName

data class LocationUserResponse(
    @SerializedName("city") val city: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("state") val state: String,
    @SerializedName("street") val street: String
)