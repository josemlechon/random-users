package com.jml.random.users.users.data.model.db.pages

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PageInfoEntity.TABLE)
data class PageInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = PageInfoEntity.ID) val uuid: String,
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "results") val results: Int,
    @ColumnInfo(name = "seed") val seed: String,
    @ColumnInfo(name = "version") val version: String
) {

    companion object {
        const val TABLE = "pages"
        const val ID = "id"

        const val PAGE_USERS = "id_user_page"
    }

    fun nextPaege(): Int = page + 1
}