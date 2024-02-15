package org.beauty.auth.service.impl

import org.beauty.auth.config.JwtProperties
import org.beauty.auth.controller.auth.AuthenticationRequest
import org.beauty.auth.controller.auth.AuthenticationResponse
import org.beauty.auth.service.IAuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*


@Service
class AuthenticationService (
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
) : IAuthenticationService {
    override fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.phoneNumber,
                authRequest.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authRequest.phoneNumber)
        val accessToken = tokenService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration),
        )
        return AuthenticationResponse(
            accessToken
        )
    }
}
