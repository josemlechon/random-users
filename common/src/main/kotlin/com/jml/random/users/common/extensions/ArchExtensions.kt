package com.jml.random.users.common.extensions

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jml.random.users.common.view.BaseActivity
import com.jml.random.users.common.view.BaseFragment

inline fun <reified T : ViewModel> BaseActivity.getViewModel(
    viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> BaseFragment.getViewModel(
    viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> BaseFragment.getParentViewModel(
    viewModelFactory: ViewModelProvider.Factory): T {
    if (parentFragment !is BaseFragment) throw java.lang.RuntimeException("parentFragment is not BaseFragment")
    return ViewModelProviders.of(parentFragment as BaseFragment, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(
    viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(
    lifecycleOwner: LifecycleOwner,
    liveData: L,
    body: (T?) -> Unit) {
    liveData.observe(lifecycleOwner, Observer(body))
}

fun <T : Any, L : LiveData<T>> BaseActivity.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <T : Any, L : LiveData<T>> BaseFragment.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}

fun <T : Any, L : LiveData<T>> BaseActivity.observeNonNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let(body) })
}

fun <T : Any, L : LiveData<T>> BaseFragment.observeNonNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { it?.let(body) })
}


fun log(message: String) {
    Log.d("Random", message)
}

fun logError(message: String? = "", error: Throwable) {
    Log.e("Random", message, error)
}

fun BaseFragment.checkKeyArgument(key: String) {
    if (arguments == null || !arguments!!.containsKey(key)) {
        throw RuntimeException("$key  key not passed as parameter")
    }
}

fun BaseActivity.checkKeyArgument(vararg keys: String) {
    keys.forEach {
        if (intent.extras == null || !intent.extras!!.containsKey(it)) {
            throw RuntimeException("$it  key not passed as parameter")
        }
    }
}

