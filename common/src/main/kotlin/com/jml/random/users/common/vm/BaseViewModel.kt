package com.jml.random.users.common.vm

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private var disposables: CompositeDisposable = CompositeDisposable()

    private val loadingLiveData = MutableLiveData<Boolean>()

    @CallSuper
    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    protected fun addDisposable(disposable: Disposable) {
        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }

        disposables.add(disposable)
    }

    protected fun blockScreen(show: Boolean = true) {
        loadingLiveData.value = show
    }

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

}