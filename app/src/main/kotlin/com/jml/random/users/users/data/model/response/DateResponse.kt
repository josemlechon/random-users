package com.jml.random.users.users.data.model.response

import com.google.gson.annotations.SerializedName

data class DateResponse(
    @SerializedName("age") val age: Int,
    @SerializedName("date") val date: String
)


