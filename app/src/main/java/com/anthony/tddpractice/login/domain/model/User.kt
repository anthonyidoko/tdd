package com.anthony.tddpractice.login.domain.model

class User(
    val name: String,
    val username: String,
    val occupation: UserOccupation
)

enum class UserOccupation{
    Teacher(),
    Farmer(),
    Doctor()
}
