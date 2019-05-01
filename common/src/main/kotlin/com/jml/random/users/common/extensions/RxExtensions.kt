package com.jml.random.users.common.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers

fun <T> Maybe<T>.observeOnMainThread() =
    this.observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.subscribeOnIO() =
    this.subscribeOn(Schedulers.io())

fun <T> Maybe<T>.subscribeOnComputation() =
    this.subscribeOn(Schedulers.computation())

fun <T> Single<T>.observeOnMainThread() =
    this.observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeOnIO() =
    this.subscribeOn(Schedulers.io())

fun <T> Single<T>.subscribeOnComputation() =
    this.subscribeOn(Schedulers.io())

fun Completable.observeOnMainThread() =
    this.observeOn(AndroidSchedulers.mainThread())

fun Completable.subscribeOnIO() =
    this.subscribeOn(Schedulers.io())

fun Completable.subscribeOnComputation() =
    this.subscribeOn(Schedulers.io())

fun <T> Flowable<T>.subscribeOnIO() =
    this.subscribeOn(Schedulers.io())

fun <T> Flowable<T>.subscribeOnComputation() =
    this.subscribeOn(Schedulers.computation())

fun <T> Flowable<T>.observeOnMainThread() =
    this.observeOn(AndroidSchedulers.mainThread())

