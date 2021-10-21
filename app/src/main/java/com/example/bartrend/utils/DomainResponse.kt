package com.example.bartrend.utils


sealed class DomainResponse<out T : Any> {

    data class Success<out T : Any>(val data: T) : DomainResponse<T>()
    data class Error(val message: String) : DomainResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[Message=$message]"
        }
    }

    fun success(operation: (T) -> Unit): DomainResponse<T> {
        if (this is Success<T>) operation(this.data)
        return this
    }

    fun <E: Any> mapSuccess(operation: (T) -> E): DomainResponse<E> {
        return when (this) {
            is Success -> Success(operation(this.data))
            is Error -> Error(this.message)
        }
    }

    fun failure(operation: (String) -> Unit): DomainResponse<T> {
        if (this is Error) operation(this.message)
        return this
    }

    fun isSuccess(): Boolean {
        return this is Success<*>
    }
}