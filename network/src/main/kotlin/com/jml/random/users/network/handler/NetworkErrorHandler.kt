package com.jml.random.users.network.handler

import com.jml.random.users.network.exceptions.CredentialsException
import com.jml.random.users.network.exceptions.NetworkException
import com.jml.random.users.network.exceptions.UnknownHttpException
import io.reactivex.*

import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

object NetworkErrorHandler {

    fun <T> parseHttpErrors(): ObservableTransformer<T, T> {

        return ObservableTransformer { transformer ->
            transformer.onErrorResumeNext { throwable: Throwable ->
                Observable.error(handleError(throwable))
            }
        }
    }



    fun <T> parseSingleHttpErrors(): SingleTransformer<T, T> {

        return SingleTransformer { transformer ->
            transformer
                .onErrorResumeNext { throwable: Throwable ->
                    Single.error(handleError(throwable))
                }
        }
    }

    fun parseCompletableHttpErrors(): CompletableTransformer {

        return CompletableTransformer { transformer ->
            transformer
                .onErrorResumeNext { throwable: Throwable ->
                    Completable.error(handleError(throwable))
                }
        }
    }

    private fun handleError(throwable: Throwable): Exception {
        return if (throwable is HttpException) {
            validateHttpExceptions(throwable)
        } else if (throwable is UnknownHostException || throwable is IOException) {
            NetworkException()
        } else {
            throwable as Exception
        }
    }

    private fun validateHttpExceptions(throwable: Throwable): Exception {
        return when (val errorCode = (throwable as HttpException).response().code()) {
            HttpResponseCodes.HTTP_RESPONSE_UNAUTHORIZED -> CredentialsException()
            HttpResponseCodes.HTTP_RESPONSE_TIMEOUT -> NetworkException()
            HttpResponseCodes.HTTP_RESPONSE_BAD_REQUEST,
            HttpResponseCodes.HTTP_RESPONSE_NOT_FOUND,
            HttpResponseCodes.HTTP_RESPONSE_FORBIDDEN,
            HttpResponseCodes.HTTP_RESPONSE_INTERNAL_SERVER_ERROR ->
                UnknownHttpException(errorCode, throwable.message())
            else -> UnknownHttpException(errorCode, throwable.message())
        }
    }
}
