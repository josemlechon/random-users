package com.jml.random.users.common.exceptions

import com.jml.random.users.network.exceptions.NetworkException

enum class ErrorType {
    GENERIC,
    NETWORK;

    companion object {

        fun from(exception: Throwable): ErrorType {
            return when (exception) {
                is NetworkException -> NETWORK
                else -> GENERIC
            }
        }
    }
}