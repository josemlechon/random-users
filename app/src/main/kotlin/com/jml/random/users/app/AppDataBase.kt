package com.jml.random.users.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jml.random.users.users.data.datasource.UserDAODataSource
import com.jml.random.users.users.data.model.db.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_database"
    }


    abstract fun userDao(): UserDAODataSource
}