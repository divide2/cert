package com.divide2.security

import com.divide2.core.token.InMemoryTokenStore
import com.divide2.core.token.TokenStore
import com.vladmihalcea.hibernate.type.array.StringArrayType
import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.service.ServiceRegistry
import org.hibernate.boot.MetadataBuilder
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder
import javax.persistence.EntityManagerFactory


/**
 * @author bvvy
 * @date 2019/10/7
 */
@Configuration
class SystemConfig {
    @Bean
    fun tokenStore(): TokenStore {
        return InMemoryTokenStore()
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}