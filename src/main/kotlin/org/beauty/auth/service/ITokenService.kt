package org.beauty.auth.service

import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.crypto.SecretKey

interface ITokenService {
    val secretKey: SecretKey?
    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String

    fun extractPhoneNumber(token: String): String?
    fun isExpired(token: String) : Boolean
    fun isValid(token: String, userDetails: UserDetails): Boolean
    fun getAllClaims(token: String): Claims
}