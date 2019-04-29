package com.jml.random.users.network.model

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: Int,
    @SerializedName("seed") val seed: String,
    @SerializedName("version") val version: String
)