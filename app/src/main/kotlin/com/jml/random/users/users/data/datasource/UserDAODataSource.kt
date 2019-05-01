package com.jml.random.users.users.data.datasource

import androidx.room.*
import com.jml.random.users.users.data.model.db.user.DeletedUserEntity
import com.jml.random.users.users.data.model.db.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class UserDAODataSource {

    @Query(
        "SELECT ${UserEntity.TABLE}.* FROM ${UserEntity.TABLE} WHERE " +
                " ${UserEntity.FIELD_ID} NOT IN (SELECT ${DeletedUserEntity.FIELD_ID} FROM  ${DeletedUserEntity.TABLE}) " +
                " ORDER BY ${UserEntity.KEY_ID}"
    )
    abstract fun getAll(): Maybe<List<UserEntity>>

    @Query(
        "SELECT ${UserEntity.TABLE}.* FROM ${UserEntity.TABLE} , ${DeletedUserEntity.TABLE} WHERE " +
                " (name_first like '%' || :filter || '%'  OR " +
                " name_last like '%' || :filter || '%'  OR " +
                " email like '%' || :filter || '%' )  and ${UserEntity.FIELD_ID} not like ${DeletedUserEntity.FIELD_ID} " +
                " GROUP by ${UserEntity.FIELD_ID}" +
                " ORDER BY ${UserEntity.KEY_ID}"
    )
    abstract fun getUsersByFilter(filter: String): Single<List<UserEntity>>

    @Query("SELECT * FROM ${UserEntity.TABLE} WHERE ${UserEntity.FIELD_ID}  = :id LIMIT 1")
    abstract fun getUserById(id: String): Maybe<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(user: UserEntity): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAll(listUsers: List<UserEntity>): Completable

    @Query("DELETE  FROM ${UserEntity.TABLE}  WHERE ${UserEntity.FIELD_ID}  = :id")
    abstract fun delete(id: String): Completable

    @Query(
        "SELECT count(*) FROM ${UserEntity.TABLE}, ${DeletedUserEntity.TABLE} WHERE " +
                " ${UserEntity.FIELD_ID}  = :id  OR " +
                " ${DeletedUserEntity.FIELD_ID} = :id "
    )
    abstract fun existID(id: String): Int
}