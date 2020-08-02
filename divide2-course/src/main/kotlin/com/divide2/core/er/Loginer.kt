package com.divide2.core.er

import com.divide2.core.token.StoreUser
import com.divide2.course.model.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder

object Loginer {

    /**
     * 登录id
     */
    fun getId(): Int {
        return loginer().id
    }

    /**
     * 获取登录用户
     */
    private fun loginer(): StoreUser {
        return SecurityContextHolder.getContext().authentication.principal as StoreUser
    }

}