package com.jml.random.users.network.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
   @SerializedName("results") val results: T,
   @SerializedName("info") val info : InfoResponse
)