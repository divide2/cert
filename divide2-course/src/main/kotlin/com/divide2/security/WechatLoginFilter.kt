package com.divide2.security

import com.divide2.core.token.AccessToken
import com.divide2.core.token.StoreUser
import com.divide2.core.token.TokenStore
import com.divide2.course.service.UserService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class WechatLoginFilter(var tokenStore: TokenStore) : OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val requestHeader = request.getHeader("Authorization")
        var storeUser: StoreUser? = null
        var authToken: String?
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7)
            storeUser = tokenStore.extract(AccessToken(authToken))
        }

        if (storeUser != null) {
            // todo 缓存
//            val user = userService.getById(storeUser.id)
//            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
            //todo 权限控制
            val authentication = UsernamePasswordAuthenticationToken(storeUser, null, listOf(GrantedAuthority { "ROLE_USER" }))
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
//            }
        }
        chain.doFilter(request, response)

    }
}