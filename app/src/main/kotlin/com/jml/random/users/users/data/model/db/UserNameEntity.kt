package com.jml.random.users.users.data.model.db

import androidx.room.ColumnInfo

data class UserNameEntity(
    @ColumnInfo(name = "name_first")val first: String,
    @ColumnInfo(name = "name_last")val last: String,
    @ColumnInfo(name = "name_title") val title: String

)