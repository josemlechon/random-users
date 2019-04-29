package com.jml.random.users.common.extensions

import com.jml.random.users.common.exceptions.ErrorType

fun Throwable.getType(): ErrorType {
    return ErrorType.from(this)
}