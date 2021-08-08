package com.divide2.core.er

import com.divide2.core.token.StoreUser
import org.springframework.security.core.context.SecurityContextHolder

object Loginer {

    /**
     * 登录id
     */
    fun getId(): Int {
        return loginer().id
    }

    fun isAuth(): Boolean {
        return SecurityContextHolder.getContext().authentication.isAuthenticated
    }

    /**
     * 获取登录用户
     */
    private fun loginer(): StoreUser {
        return SecurityContextHolder.getContext().authentication.principal as StoreUser
    }

}
