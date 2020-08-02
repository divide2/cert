package com.divide2.sys.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonView
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sys_org")
class Org(
        @Id
        @GeneratedValue
        @JsonView(UserOrgVO::class)
        var id: Int = 0,
        @JsonView(UserOrgVO::class)
        var avatar: String = "",
        @JsonView(UserOrgVO::class)
        var name: String = "",
        @Transient
        @JsonView(UserOrgVO::class)
        var isFavorite: Boolean = false,
        @JsonIgnore
        var password: String = "",
        @JsonIgnore
        var license: String = "",
        @JsonView(OrgInfo::class)
        var status: String = "",
        @JsonView(OrgInfo::class)
        var contactUser: String = "",
        @JsonView(OrgInfo::class)
        var contactWay: String = "",
        @JsonView(OrgInfo::class)
        var address: String = "",
        @JsonView(OrgInfo::class)
        var email: String = "",
        @JsonView(OrgInfo::class)
        var createAt: LocalDateTime = LocalDateTime.now(),
        @JsonView(OrgInfo::class)
        var updateAt: LocalDateTime = LocalDateTime.now(),

        @JsonView(OrgInfo::class)
        @Transient
        var fans: Int = 0
) {
    interface UserOrgVO

    interface OrgInfo : UserOrgVO
}


