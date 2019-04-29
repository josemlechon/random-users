package com.jml.random.users.users.data.datasource

import androidx.room.*
import com.jml.random.users.users.data.model.db.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
abstract class UserDAODataSource {

    @Query("SELECT * FROM ${UserEntity.TABLE}")
    abstract fun getAll(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM ${UserEntity.TABLE} WHERE ${UserEntity.ID}  = :id LIMIT 1")
    abstract fun findFavById(id: String): Maybe<UserEntity>

    @Query("SELECT * FROM ${UserEntity.TABLE} WHERE ${UserEntity.ID}  = :first LIMIT 1")
    protected abstract fun findById(first: String): Flowable<UserEntity>

    fun getDistinctUserById(id: String): Flowable<UserEntity> {
        return findById(id).distinctUntilChanged()
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(user: UserEntity): Completable

    @Delete
    abstract fun delete(user: UserEntity): Completable
}