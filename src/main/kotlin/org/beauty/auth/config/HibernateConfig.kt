package org.beauty.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.hibernate5.LocalSessionFactoryBean


@Configuration
class HibernateConfig {

    @Bean
    fun sessionFactory(): LocalSessionFactoryBean {
        val sessionFactory = LocalSessionFactoryBean()
        sessionFactory.setPackagesToScan("org.beauty.auth.repository.entity")
        return sessionFactory
    }
}