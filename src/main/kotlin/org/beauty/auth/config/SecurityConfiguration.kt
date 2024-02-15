package org.beauty.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean

    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): DefaultSecurityFilterChain = http
        .authorizeHttpRequests {
            it
                .requestMatchers(*unauthorizedUrls)
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/user/create")
                .permitAll()
                .anyRequest()
                .fullyAuthenticated()
        }
        .sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        .csrf { it.disable() }
        .build()


    @Bean(name = ["getUserByPhoneNumberSecurity"])
    fun hasUserId(authentication: Authentication?, userId: Long?): Boolean {
        println(authentication);
        return true;
    }

    val unauthorizedUrls = arrayOf(
        "/api/user/exists/*", "/api/auth", "/error", "/favicon.ico", "/api/mock/code/**",
        "/index.html", "/index.js", "/auth/*", "/registration/*", "/userInfo/*", "/style.css", "/graphql", "/graphiql"
    )
}