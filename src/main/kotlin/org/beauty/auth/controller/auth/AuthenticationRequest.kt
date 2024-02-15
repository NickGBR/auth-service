package org.beauty.auth.controller.auth

data class AuthenticationRequest (
    val phoneNumber: String,
    val password: String
)
