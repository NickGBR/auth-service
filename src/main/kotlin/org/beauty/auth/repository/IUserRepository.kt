package org.beauty.auth.repository

import org.beauty.auth.repository.entity.UserEntity

interface IUserRepository {
    fun save(user: UserEntity)
    fun findUserByPhoneNumber(phoneNumber: String): UserEntity?
}