package org.beauty.auth.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface ICustomUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails
}