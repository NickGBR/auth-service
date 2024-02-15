package org.beauty.auth.controller.user

import java.util.UUID

data class UserResponse (
    val name: String,
    val surname: String,
    val phoneNumber: String
)