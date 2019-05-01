package com.jml.random.users.home.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jml.random.users.common.domain.model.PaginationScroll
import com.jml.random.users.common.extensions.getType
import com.jml.random.users.common.extensions.log
import com.jml.random.users.common.extensions.logError
import com.jml.random.users.common.extensions.observeOnMainThread
import com.jml.random.users.common.view.vm.BaseViewModel
import com.jml.random.users.home.domain.GetMoreUsers
import com.jml.random.users.home.view.vm.state.HomeState
import com.jml.random.users.home.domain.GetUsers
import io.reactivex.rxkotlin.subscribeBy

class HomeViewModel(
    private val getUsers: GetUsers,
    private val getMoreUsers: GetMoreUsers

) : BaseViewModel() {

    val pagination: PaginationScroll = PaginationScroll()

    private val homeStateLiveData: MutableLiveData<HomeState> = MutableLiveData()

    init {
        requestUsers()
    }

    private fun requestUsers() {
        getUsers.execute()
            .observeOnMainThread()
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

        pagination.loading = true
        getMoreUsers.execute()
            .observeOnMainThread()
            .doFinally {
                pagination.loading = false
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


    fun refreshUsers() {

    }

    fun getHomeStateLiveData(): LiveData<HomeState> = homeStateLiveData
}