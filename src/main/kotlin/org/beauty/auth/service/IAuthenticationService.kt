package org.beauty.auth.service

import org.beauty.auth.controller.auth.AuthenticationRequest
import org.beauty.auth.controller.auth.AuthenticationResponse

interface IAuthenticationService {
    fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse

}