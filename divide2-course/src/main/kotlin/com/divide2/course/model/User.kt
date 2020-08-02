package com.divide2.course.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonView
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author bvvy
 *
 * @date 2019/10/5
 */
@Entity
@Table(name = "sys_user")
class User(
        @Id
        @GeneratedValue
        @JsonView(UserVO::class)
        var id: Int = 0,
        @JsonIgnore
        var openId: String = "",
        @JsonView(UserVO::class)
        var nickname: String = "",
        @JsonView(UserVO::class)
        var avatar: String = "",
        @JsonIgnore
        var city: String = "",
        @JsonIgnore
        var country: String = "",
        @JsonView(UserVO::class)
        var gender: String = "",
        @JsonIgnore
        var language: String = "",
        @JsonIgnore
        var province: String = "",
        @JsonView(UserInfo::class)
        var username: String = "",
        @JsonIgnore
        var phoneNumber: String = "",
        @JsonIgnore
        var password: String = "",
        @JsonView(UserInfo::class)
        var email: String = "",
        @JsonIgnore
        var status: String = "",
        @JsonView(UserInfo::class)
        var address: String = "",
        @JsonView(UserInfo::class)
        var createTime: LocalDateTime = LocalDateTime.now(),
        @JsonView(UserInfo::class)
        var updateTime: LocalDateTime = LocalDateTime.now(),
        @Transient
        var favorite: Int = 0

) {
    interface UserVO

    interface UserInfo : UserVO
}