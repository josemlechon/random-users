package com.jml.random.users.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jml.random.users.users.data.datasource.DeletedUserDAODataSource
import com.jml.random.users.users.data.datasource.PagesDAODataSource
import com.jml.random.users.users.data.datasource.UserDAODataSource
import com.jml.random.users.users.data.model.db.pages.PageInfoEntity
import com.jml.random.users.users.data.model.db.user.DeletedUserEntity
import com.jml.random.users.users.data.model.db.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PageInfoEntity::class,
        DeletedUserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_database"
    }

    abstract fun userDao(): UserDAODataSource
    abstract fun pagesDao(): PagesDAODataSource
    abstract fun deleteUserDao(): DeletedUserDAODataSource
}