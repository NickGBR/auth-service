package org.beauty.auth.repository.entity

import jakarta.persistence.*
import org.beauty.auth.enums.Role
import java.util.*

@Entity
@Table(name = "Users")
data class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val password: String,
    val role: Role
)