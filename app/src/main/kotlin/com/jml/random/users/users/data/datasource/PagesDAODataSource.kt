package com.jml.random.users.users.data.datasource

import androidx.room.*
import com.jml.random.users.users.data.model.db.pages.PageInfoEntity
import com.jml.random.users.users.data.model.db.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
abstract class PagesDAODataSource {

    @Query("SELECT * FROM ${PageInfoEntity.TABLE}  WHERE ${PageInfoEntity.ID}  = :id  LIMIT 1")
    abstract fun getUsersPage(id: String = PageInfoEntity.PAGE_USERS): Maybe<PageInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(pageInfo: PageInfoEntity): Completable

    @Delete
    abstract fun delete(user: UserEntity): Completable
}