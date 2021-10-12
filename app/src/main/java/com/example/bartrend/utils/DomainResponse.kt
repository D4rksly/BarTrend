package utils

import kotlin.contracts.ReturnsNotNull


sealed class DomainResponse<out T : Any> {

    data class Success<out T : Any>(val data: T) : DomainResponse<T>()
    data class Error(val message: String) : DomainResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[Message=$message]"
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun success(operation: (T) -> Unit): DomainResponse <T> {
        if (this is Success<*>) operation(this.data as T)
        return this
    }

    fun failure(operation: (String) -> Unit): DomainResponse <T> {
        if (this is Error) operation(this.message)
        return this
    }

    fun isSuccess(): Boolean {
        return this is Success<*>
    }
}