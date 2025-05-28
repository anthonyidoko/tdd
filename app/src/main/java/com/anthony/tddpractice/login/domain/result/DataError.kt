package com.anthony.tddpractice.login.domain.result

sealed interface Error

sealed interface DataError: Error {
    enum class NetworkError: DataError{
        NotFound

    }

    enum class LocalError: DataError{

    }
}