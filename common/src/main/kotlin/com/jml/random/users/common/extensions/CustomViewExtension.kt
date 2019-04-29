package com.jml.random.users.common.extensions

import android.content.res.TypedArray

fun TypedArray.applyReference(idStyleable: Int, applyToFunction: (resource: Int) -> Unit) {
    takeIf { it.hasValue(idStyleable) }
        ?.let { getResourceId(idStyleable, -1) }
        ?.takeIf { it != -1 }
        ?.let(applyToFunction)
}