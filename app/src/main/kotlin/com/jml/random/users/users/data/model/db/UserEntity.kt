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
    @Embedded val name: UserNameEntity, //@ColumnInfo(name = "name")
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @Embedded val photo: UserPhotoEntity, //@ColumnInfo(name = "photos")
    @ColumnInfo(name = "registered") val registered: String?, //@ColumnInfo(name = "registered")
    @ColumnInfo(name = "gender") val gender: String,
    @Embedded val location: UserLocationEntity //@ColumnInfo(name = "location")
) {

    companion object {
        const val TABLE = "user"
        const val ID = "id"
    }
}