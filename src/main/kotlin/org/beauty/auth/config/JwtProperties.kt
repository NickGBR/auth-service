package org.beauty.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@ConfigurationProperties("jwt")
data class JwtProperties (
    val key: String,
    val accessTokenExpiration: Long,
)