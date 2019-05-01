package com.jml.random.users.users.data.model.db.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = DeletedUserEntity.TABLE)
data class DeletedUserEntity(

    @PrimaryKey
    @ColumnInfo(name = ID) val uuid: String,
    @ColumnInfo(name = "username") val username: String
) {

    companion object {
        const val TABLE = "deleted_user"
        const val ID = "id"

        const val FIELD_ID = "$TABLE.$ID"

    }
}