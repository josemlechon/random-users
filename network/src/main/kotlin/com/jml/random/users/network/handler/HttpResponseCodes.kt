package com.jml.random.users.network.handler

class HttpResponseCodes {

    companion object {
        const val HTTP_RESPONSE_OK = 200
        const val HTTP_RESPONSE_BAD_REQUEST = 400
        const val HTTP_RESPONSE_UNAUTHORIZED = 401
        const val HTTP_RESPONSE_FORBIDDEN = 403
        const val HTTP_RESPONSE_NOT_FOUND = 404
        const val HTTP_RESPONSE_TIMEOUT = 408
        const val HTTP_RESPONSE_INTERNAL_SERVER_ERROR = 500
    }
}
