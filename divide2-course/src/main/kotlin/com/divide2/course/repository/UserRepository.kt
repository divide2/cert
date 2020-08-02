package com.divide2.course.repository

import com.divide2.course.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author bvvy
 * @date 2019/10/5
 */
interface UserRepository : JpaRepository<User, Int> {

    fun findByOpenId(openId: String): User?

    fun findByUsername(username: String): User?

    fun getByPhoneNumber(phoneNumber: String): User?
}