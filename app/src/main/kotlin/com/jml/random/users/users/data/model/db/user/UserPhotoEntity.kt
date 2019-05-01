package com.jml.random.users.users.data.model.db.user

import androidx.room.ColumnInfo


data class UserPhotoEntity(
    @ColumnInfo(name = "large_photo") val large: String,
    @ColumnInfo(name = "medium_photo") val medium: String,
    @ColumnInfo(name = "thumbnail_photo") val thumbnail: String

)