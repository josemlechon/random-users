package com.jml.random.users.users.data.repository

import com.jml.random.users.common.extensions.subscribeOnComputation
import com.jml.random.users.common.extensions.subscribeOnIO
import com.jml.random.users.users.data.datasource.PagesDAODataSource
import com.jml.random.users.users.data.datasource.UserDAODataSource
import com.jml.random.users.users.data.datasource.UserRemoteDataSource
import com.jml.random.users.users.data.mapper.PaggingMapper
import com.jml.random.users.users.data.mapper.UsersMapper
import com.jml.random.users.users.data.model.db.pages.PageInfoEntity
import com.jml.random.users.users.data.model.db.user.UserEntity
import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single


class UserRepositoryImpl(
    private val usersRemoteDS: UserRemoteDataSource,
    private val userDataBaseDS: UserDAODataSource,
    private val pagesDataBaseDS: PagesDAODataSource
) : UserRepository {


    override fun getLocalUsers(): Maybe<List<User>> {
        return userDataBaseDS.getAll()
            .subscribeOnComputation()
            .map(UsersMapper::mapFromUsersEntityToModel)
    }

    override fun getUsers(): Single<List<User>> {
        return pagesDataBaseDS.getUsersPage()
            .subscribeOnIO()
            .flatMap { usersRemoteDS.requestUsers(it.nextPaege(), it.results, it.seed).toMaybe() }
            .switchIfEmpty(usersRemoteDS.requestInitialUsers())
            .map {
                Pair(
                    first = PaggingMapper.fromUserResponseToEntity(it.info),
                    second = UsersMapper.mapFromUserResponseToEntity(it.results)
                )
            }
            .flatMap {
                storeUserPage(it).toSingle { it.second }
            }
            .map(UsersMapper::mapFromUsersEntityToModel)
    }

    private fun storeUserPage(data: Pair<PageInfoEntity, List<UserEntity>>): Completable {

        return Completable.concat(
            listOf(
                pagesDataBaseDS.insert(data.first),
                userDataBaseDS.insertAll(data.second)
            )
        )
    }

    override fun getUser(id: String): Maybe<User> {
        return userDataBaseDS.findUserById(id)
            .subscribeOnComputation()
            .map(UsersMapper::mapFromUserEntityToModel)

    }

   override fun getFilterUsers(filter: String): Single<List<User>>{
        return userDataBaseDS.findUsersByFilter(filter)
            .subscribeOnComputation()
            .map(UsersMapper::mapFromUsersEntityToModel)
    }

    override fun deleteUser(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}