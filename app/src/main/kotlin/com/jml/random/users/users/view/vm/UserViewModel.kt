package com.jml.random.users.users.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jml.random.users.common.extensions.getType
import com.jml.random.users.common.extensions.log
import com.jml.random.users.common.extensions.observeOnMainThread
import com.jml.random.users.common.view.vm.BaseViewModel
import com.jml.random.users.users.domain.usecases.GetUser
import com.jml.random.users.users.view.vm.state.UserState
import io.reactivex.rxkotlin.subscribeBy

class UserViewModel constructor(
    private val getUser: GetUser
) : BaseViewModel() {

    private var idUser: String = ""
    private val userLiveData: MutableLiveData<UserState> = MutableLiveData()

    init {
    }

    fun setUserId(id: String) {
        this.idUser = id
    }

    fun getUser() {

        blockScreen()

        getUser.execute(idUser)
            .observeOnMainThread()
            .doFinally { blockScreen(false) }
            .subscribeBy(
                onSuccess = {
                    userLiveData.value = UserState.Data(it)
                },
                onError = {
                    it.log()
                    userLiveData.value = UserState.Error(it.getType())
                }
            ).also(::addDisposable)
    }

    fun getUserLiveData(): LiveData<UserState> = userLiveData

}