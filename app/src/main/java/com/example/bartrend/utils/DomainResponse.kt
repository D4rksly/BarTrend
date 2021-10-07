package com.example.bartrend.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */

sealed class DomainResponse<out T : Any> {

    data class Success<out T : Any>(val data: T) : DomainResponse<T>()
    data class Error(val message: String) : DomainResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[Message=$message]"
        }
    }
}