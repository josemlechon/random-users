package com.jml.random.users.network.exceptions

/**
 * Exception send whenever there's a Http exception not handled
 */
class UnknownHttpException(val code: Int, message: String?) : Exception(message)
