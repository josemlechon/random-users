package com.jml.random.users.home.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jml.random.users.common.domain.model.PaginationScroll
import com.jml.random.users.common.extensions.getType
import com.jml.random.users.common.extensions.logError
import com.jml.random.users.common.extensions.observeOnMainThread
import com.jml.random.users.common.view.vm.BaseViewModel
import com.jml.random.users.home.view.vm.state.HomeState
import com.jml.random.users.home.domain.GetUsers
import io.reactivex.rxkotlin.subscribeBy

class HomeViewModel(
    private val getUsers: GetUsers
) : BaseViewModel() {

    private val homeStateLiveData: MutableLiveData<HomeState> = MutableLiveData()

    val pagination: PaginationScroll = PaginationScroll()

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
    }


    fun refreshUsers() {

    }

    fun getHomeStateLiveData(): LiveData<HomeState> = homeStateLiveData
}