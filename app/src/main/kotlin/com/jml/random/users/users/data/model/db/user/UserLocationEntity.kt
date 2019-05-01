package com.jml.random.users.users.data.model.db.user

import androidx.room.ColumnInfo

data class UserLocationEntity(
    @ColumnInfo(name = "location_street") val street: String?,
    @ColumnInfo(name = "location_city") val city: String?,
    @ColumnInfo(name = "location_state") val state: String?

)