package com.jml.random.users.users.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = UserEntity.TABLE)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = ID) val uuid: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "name") @Embedded val name: UserNameEntity,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "photos") @Embedded val photo: UserPhotoEntity,
    @ColumnInfo(name = "registered") @Embedded val registered: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "location") @Embedded val location: UserLocationEntity
) {

    companion object {
        const val TABLE = "user"
        const val ID = "id"
    }
}