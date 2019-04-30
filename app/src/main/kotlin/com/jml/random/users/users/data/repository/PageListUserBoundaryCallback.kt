package com.jml.random.users.users.data.repository

import androidx.paging.PagedList
import com.jml.random.users.common.extensions.log
import com.jml.random.users.common.extensions.logError
import com.jml.random.users.common.extensions.subscribeOnIO
import com.jml.random.users.users.data.datasource.UserDAODataSource
import com.jml.random.users.users.data.datasource.UserRemoteDataSource
import com.jml.random.users.users.data.mapper.UsersMapper
import com.jml.random.users.users.data.model.db.UserEntity
import com.jml.random.users.users.domain.model.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class PageListUserBoundaryCallback(
    private val usersRemoteDS: UserRemoteDataSource,
    private val userDataBaseDS: UserDAODataSource
) : PagedList.BoundaryCallback<User>() {

    companion object {
        const val NUM_ITEMS_PAGE = 10
    }

    private var disposables: CompositeDisposable = CompositeDisposable()

    private var isRequestRunning = false
    private var requestedPage = 1

    override fun onZeroItemsLoaded() {
        log("onZeroItemsLoaded")
        fetchUsers()
    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {
        log("onItemAtEndLoaded")
        fetchUsers()
    }


    private fun fetchUsers() {
        if (isRequestRunning) return

        isRequestRunning = true

        usersRemoteDS.requestUsers(requestedPage, NUM_ITEMS_PAGE)
            .map { it.results }
            .map(UsersMapper::mapFromUserResponseToEntity)
            .filter { it.isNotEmpty() }
            .subscribeOnIO()
            .doFinally { isRequestRunning = false }
            .subscribeBy(
                onError = { logError(error = it) },
                onSuccess = ::storeUsers
            ).also(::addDisposable)

    }

    private fun storeUsers(users: List<UserEntity>) {
        userDataBaseDS.insertAll(users)
        log("Inserted: ${users.size}")
        log("Fetch Completed")
        requestedPage++

    }

    private fun addDisposable(disposable: Disposable) {
        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }

        disposables.add(disposable)
    }

    private fun clear() {
        disposables.clear()
    }
}