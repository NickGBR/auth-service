package org.beauty.auth.service.impl

import org.beauty.auth.enums.Role
import org.beauty.auth.mock.CodeGenerationService
import org.beauty.auth.model.User
import org.beauty.auth.repository.impl.UserRepository
import org.beauty.auth.repository.entity.UserEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val encoder: PasswordEncoder,
    private val codeGenerationService: CodeGenerationService
) {
    fun createUser(user: User, code: String): User? {
        val foundUser = repository.findUserByPhoneNumber(user.phoneNumber)
        return if (codeGenerationService.getCode(user.phoneNumber)?.equals(code) != true || foundUser != null) {
            null
        } else {
            repository.save(user.toUserEntity())
            return user
        }
    }

    fun findByPhoneNumber(phoneNumber: String) =
        repository.findUserByPhoneNumber(phoneNumber)?.toUser()

    fun userExists(phoneNumber: String): Boolean =
        repository.findUserByPhoneNumber(phoneNumber) != null

    private fun UserEntity.toUser() =
        User(
            id = this.id,
            name = this.name,
            surname = this.surname,
            phoneNumber = this.phoneNumber,
            password = this.password,
            role = Role.USER
        )

    private fun User.toUserEntity(): UserEntity =
        UserEntity(
            name = this.name,
            surname = this.surname,
            password = encoder.encode(this.password),
            phoneNumber = this.phoneNumber,
            role = this.role
        )
}