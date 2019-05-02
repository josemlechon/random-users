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
import com.jml.random.users.users.domain.usecases.DeleteUser
import com.jml.random.users.home.domain.usecases.GetMoreHomeUsers
import com.jml.random.users.home.domain.usecases.FilterHomeUsers
import com.jml.random.users.home.view.vm.state.HomeState
import com.jml.random.users.home.domain.usecases.GetHomeUsers
import es.lacaixa.voluntariado.android.core.common.vm.SingleLiveEvent
import io.reactivex.rxkotlin.subscribeBy

class HomeViewModel(
    private val getUsers: GetHomeUsers,
    private val getMoreUsers: GetMoreHomeUsers,
    private val filterUsers: FilterHomeUsers,
    private val deleteUser: DeleteUser
) : BaseViewModel() {

    val pagination: PaginationScroll = PaginationScroll()

    private val homeStateLiveData: MutableLiveData<HomeState> = MutableLiveData()


    init {
        requestUsers()
    }

    fun requestUsers() {
        blockScreen()

        getUsers.execute()
            .observeOnMainThread()
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
            .subscribeBy(
                onSuccess = {
                    homeStateLiveData.value = HomeState.AddData(it)
                },
                onError = {
                    it.log()
                    homeStateLiveData.value = HomeState.ErrorMoreData(it.getType())
                }
            ).also(::addDisposable)
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