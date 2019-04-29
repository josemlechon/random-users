package com.jml.random.users.users.data.model

import com.google.gson.annotations.SerializedName

data class LocationUser(
    @SerializedName("city") val city: String,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("postcode") val postcode: Int,
    @SerializedName("state") val state: String,
    @SerializedName("street") val street: String,
    @SerializedName("timezone") val timezone: Timezone
)