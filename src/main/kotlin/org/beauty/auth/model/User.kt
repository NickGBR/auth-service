package org.beauty.auth.model

import org.beauty.auth.enums.Role
import java.util.UUID

data class User (
    val id: UUID? = null,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val password: String,
    val role: Role
)
