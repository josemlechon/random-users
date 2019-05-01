package com.jml.random.users.users.data.model.db.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = KEY_ID) val keyID: Int = 0,
    @ColumnInfo(name = UUID) val uuid: String,
    @ColumnInfo(name = "username") val username: String,
    @Embedded val name: UserNameEntity,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @Embedded val photo: UserPhotoEntity,
    @ColumnInfo(name = "registered") val registered: String?,
    @ColumnInfo(name = "gender") val gender: String,
    @Embedded val location: UserLocationEntity
) {

    companion object {
        const val TABLE = "user"
        const val KEY_ID = "key_id"
        const val UUID = "uuid"
        const val FIELD_ID = "$TABLE.$UUID"
    }
}