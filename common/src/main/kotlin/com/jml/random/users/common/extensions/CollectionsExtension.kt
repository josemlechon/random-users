package com.jml.random.users.common.extensions

fun <R> List<R>.segmented(position: Int, segment: Int): List<R> {
    if (position > size) return listOf()
    return subList(position * segment, Math.min(size, (position * segment) + segment))
}