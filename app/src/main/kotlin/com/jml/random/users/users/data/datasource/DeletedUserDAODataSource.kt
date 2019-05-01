package com.jml.random.users.users.data.datasource

import androidx.room.*
import com.jml.random.users.users.data.model.db.user.DeletedUserEntity
import com.jml.random.users.users.data.model.db.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class DeletedUserDAODataSource {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(user: DeletedUserEntity): Completable

}