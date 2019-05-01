package com.jml.random.users.home.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jml.random.users.common.domain.model.PaginationScroll
import com.jml.random.users.common.extensions.getType
import com.jml.random.users.common.extensions.log
import com.jml.random.users.common.extensions.logError
import com.jml.random.users.common.extensions.observeOnMainThread
import com.jml.random.users.common.view.vm.BaseViewModel
import com.jml.random.users.common.view.vm.state.EventState
import com.jml.random.users.home.domain.DeleteUser
import com.jml.random.users.home.domain.FilterUsers
import com.jml.random.users.home.domain.GetMoreUsers
import com.jml.random.users.home.view.vm.state.HomeState
import com.jml.random.users.home.domain.GetUsers
import com.jml.random.users.home.view.model.UserBriefUI
import es.lacaixa.voluntariado.android.core.common.vm.SingleLiveEvent
import io.reactivex.rxkotlin.subscribeBy

class HomeViewModel(
    private val getUsers: GetUsers,
    private val getMoreUsers: GetMoreUsers,
    private val filterUsers: FilterUsers,
    private val deleteUser : DeleteUser
) : BaseViewModel() {

    val pagination: PaginationScroll = PaginationScroll()

    private val homeStateLiveData: MutableLiveData<HomeState> = MutableLiveData()

    private val listUsers: MutableList<UserBriefUI> = mutableListOf()

    init {
        requestUsers()
    }

    private fun requestUsers() {
        blockScreen()

        getUsers.execute()
            .observeOnMainThread()
            .map {
                listUsers.apply {
                    clear()
                    addAll(it)
                }
            }
            .doFinally { blockScreen(false) }
            .subscribeBy(
                onSuccess = {
                    homeStateLiveData.value = HomeState.Data(it)
                },
                onError = {
                    logError(error = it)
                    homeStateLiveData.value = HomeState.Error(it.getType())
                }
            ).also(::addDisposable)
    }

    fun getMoreUsers() {

        blockScreen()
        pagination.loading = true

        getMoreUsers.execute()
            .observeOnMainThread()
            .doFinally {
                pagination.loading = false
                blockScreen(false)
            }
            .map { listUsers.apply { addAll(it) } }
            .subscribeBy(
                onSuccess = {
                    homeStateLiveData.value = HomeState.Data(it)
                },
                onError = {
                    it.log()
                    homeStateLiveData.value = HomeState.ErrorMoreData(it.getType())
                }
            ).also(::addDisposable)
    }

    fun refreshUsers() {

    }

    fun filterUsers(search: String) {

        if (search.isEmpty()) {
            requestUsers()
            pagination.filtering = false
            return
        }

        pagination.filtering = true
        blockScreen()

        filterUsers.execute(search)
            .observeOnMainThread()
            .doFinally { blockScreen(false) }
            .subscribeBy {
                homeStateLiveData.value = HomeState.FilteredData(search, it)
            }.also(::addDisposable)
    }

    fun deleteUser(id: String): SingleLiveEvent<EventState> {

        val event: SingleLiveEvent<EventState> = SingleLiveEvent()

        blockScreen()

        deleteUser.execute(id)
            .observeOnMainThread()
            .doFinally { blockScreen(false) }
            .subscribeBy(
                onComplete = {
                    event.value = EventState.Success
                },
                onError = { error ->
                    logError(error = error)
                    event.value = EventState.Error(error.getType())

                }
            ).also(::addDisposable)

        return event
    }


    fun getHomeStateLiveData(): LiveData<HomeState> = homeStateLiveData

}