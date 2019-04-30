package com.jml.random.users.users.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.jml.random.users.common.extensions.subscribeOnIO
import com.jml.random.users.users.data.datasource.UserDAODataSource
import com.jml.random.users.users.data.datasource.UserRemoteDataSource
import com.jml.random.users.users.data.mapper.UsersMapper
import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.domain.repository.UserRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

//todo read https://github.com/erikjhordan-rey/Movies-PagingLibrary-Arch-Components/tree/master/app/src/main/kotlin/io/github/erikcaffrey/arch_components_paging_library
class UserRepositoryImpl(
    private val usersRemoteDS: UserRemoteDataSource,
    private val userDataBaseDS: UserDAODataSource
) : UserRepository {


    override fun getUsers(): Single<List<User>> {
        return usersRemoteDS.requestUsers(0, 40)
            .subscribeOnIO()
            .map { it.results }
            .map(UsersMapper::mapFromUserResponseToModel)
    }

    override fun fetchOrGetUsers(): Flowable<PagedList<User>> {
        return RxPagedListBuilder(
            userDataBaseDS.getAllUsers().map(UsersMapper::mapFromUserEntityToModel),
            PageListUserBoundaryCallback.NUM_ITEMS_PAGE
        )
            .setBoundaryCallback(PageListUserBoundaryCallback(usersRemoteDS, userDataBaseDS))
            .buildFlowable(BackpressureStrategy.LATEST)

    }
}