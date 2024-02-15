package org.beauty.auth.repository.impl

import org.beauty.auth.repository.IUserRepository
import org.beauty.auth.repository.entity.UserEntity
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val sessionFactory: SessionFactory
) : IUserRepository {
    override fun save(user: UserEntity) {
        sessionFactory.fromTransaction {
            it.persist(user)
        }
    }

    override fun findUserByPhoneNumber(phoneNumber: String): UserEntity? {
        var userEntity: UserEntity? = null
        val hqlRequest = "FROM UserEntity e WHERE e.phoneNumber = '$phoneNumber'"
        sessionFactory.inTransaction {
            userEntity = it.createQuery(hqlRequest, UserEntity::class.java).singleResultOrNull
        }
        return userEntity
    }
}
