package com.jml.random.users.users.data.repository

import com.jml.random.users.common.extensions.log
import com.jml.random.users.common.extensions.subscribeOnComputation
import com.jml.random.users.common.extensions.subscribeOnIO
import com.jml.random.users.network.model.BaseResponse
import com.jml.random.users.users.data.datasource.DeletedUserDAODataSource
import com.jml.random.users.users.data.datasource.PagesDAODataSource
import com.jml.random.users.users.data.datasource.UserDAODataSource
import com.jml.random.users.users.data.datasource.UserRemoteDataSource
import com.jml.random.users.users.data.mapper.PaggingMapper
import com.jml.random.users.users.data.mapper.UsersMapper
import com.jml.random.users.users.data.model.db.pages.PageInfoEntity
import com.jml.random.users.users.data.model.db.user.UserEntity
import com.jml.random.users.users.data.model.response.UserResponse
import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single


class UserRepositoryImpl(
    private val usersRemoteDS: UserRemoteDataSource,
    private val userDataBaseDS: UserDAODataSource,
    private val pagesDataBaseDS: PagesDAODataSource,
    private val deletedUserDataBaseDS: DeletedUserDAODataSource
) : UserRepository {

    override fun getLocalUsers(): Maybe<List<User>> {
        return userDataBaseDS.getAll()
            .subscribeOnComputation()
            .map(UsersMapper::mapFromUsersEntityToModel)
    }

    override fun getUsers(): Single<List<User>> {
        return pagesDataBaseDS.getUsersPage()
            .subscribeOnIO()
            .flatMap(::requestUsers)
            .switchIfEmpty(usersRemoteDS.requestInitialUsers())
            .map {
                Pair(
                    first = PaggingMapper.fromUserResponseToEntity(it.info),
                    second = UsersMapper.mapFromUserResponseToEntity(it.results)
                )
            }
            .flatMap(::storeUserPageData)
            .map(UsersMapper::mapFromUsersEntityToModel)
    }


    override fun getUser(id: String): Maybe<User> {
        return userDataBaseDS.getUserById(id)
            .subscribeOnComputation()
            .map(UsersMapper::mapFromUserEntityToModel)
    }

    override fun getFilterUsers(filter: String): Single<List<User>> {
        return userDataBaseDS.getUsersByFilter(filter)
            .subscribeOnComputation()
            .map(UsersMapper::mapFromUsersEntityToModel)
    }

    override fun deleteUser(id: String): Completable {
        return userDataBaseDS.getUserById(id)
            .subscribeOnComputation()
            .map(UsersMapper::mapFromUserEntityToDeleteEntity)
            .flatMapCompletable(deletedUserDataBaseDS::insert)
            .andThen(userDataBaseDS.delete(id))
    }

    private fun requestUsers(page: PageInfoEntity): Maybe<BaseResponse<List<UserResponse>>> {
        return usersRemoteDS.requestUsers(page.nextPaege(), page.results, page.seed).toMaybe()
    }

    private fun storeUserPageData(data: Pair<PageInfoEntity, List<UserEntity>>): Single<List<UserEntity>> {
        return pagesDataBaseDS.insert(data.first)
            .andThen(filterDuplicatedUsers(data.second))
            .doOnSuccess {
                log("Num users PRE filtered ${it.size}")
            }
            .flatMap {
                userDataBaseDS.insertAll(it).toSingle { it }
            }
            .doOnSuccess {
                log("Num users AFTER filtered ${it.size}")
            }
    }

    private fun filterDuplicatedUsers(usersEntity: List<UserEntity>): Single<List<UserEntity>> {
        return Observable.fromIterable(usersEntity)
            .filter { userDataBaseDS.existID(it.uuid) == 0 }
            .toList()
    }

}