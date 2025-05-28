package com.anthony.tddpractice.login.domain.result

interface Result<out T, out E: Error> {
    data class Success<out T, out E: Error>(val result: T):
        Result<T, E>
    data class Failure<E: DataError>(val error: E):
        Result<Nothing, E>
}
