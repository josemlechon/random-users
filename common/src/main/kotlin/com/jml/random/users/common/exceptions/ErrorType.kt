package com.jml.random.users.common.exceptions

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