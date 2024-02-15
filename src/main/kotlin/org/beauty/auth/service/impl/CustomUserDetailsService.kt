package org.beauty.auth.service.impl

import org.beauty.auth.repository.impl.UserRepository
import org.beauty.auth.repository.entity.UserEntity
import org.beauty.auth.service.ICustomUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service



@Service
class CustomUserDetailsService (
    private val userRepository: UserRepository
): ICustomUserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findUserByPhoneNumber(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found")


    private fun UserEntity.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.phoneNumber)
            .password(this.password)
            .roles(this.role.toString())
            .build()

}