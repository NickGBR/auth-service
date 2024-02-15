package org.beauty.auth.controller.user

data class CreateUserRequest (
    val name: String,
    val surname: String,
    val password: String,
    val phoneNumber: String,
    val code: String
)