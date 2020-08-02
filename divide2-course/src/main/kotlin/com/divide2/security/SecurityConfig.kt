package com.divide2.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * @author bvvy
 * @date 2019/10/6
 */
@Configuration
class SecurityConfig(
        var wechatLoginFilter: WechatLoginFilter
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/v1/login/**",
                        "/v2/api**",
                        "/swagger**",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v1/logout", "/v1/reg",
                        "/v1/address/city/resolve",
                        "/v1/courses",
                        "/v1/upload/image",
                        "/v1/inside/verify/code",
                        "/v1/outside/verify/code",
                        "/v1/pwd/retrieve",
                        "/v1/pwd/verify/retrieve",
                        "/v1/pwd/new",
                        "/v1/user/wechat",
                        "/v1/operate/cities").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/v1/articles",
                        "/v1/articles/*",
                        "/v1/tips",
                        "/v1/sliders").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(wechatLoginFilter, UsernamePasswordAuthenticationFilter::class.java)

    }


}

