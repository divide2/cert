package com.divide2.sys.model

import com.divide2.course.model.User
import com.fasterxml.jackson.annotation.JsonView
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "sys_favorite")
class Favorite(
        @Id
        @GeneratedValue
        val id: Int,
        @ManyToOne
        @JoinColumn(
                name = "user_id",
                foreignKey = ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
        val user: User,

        @ManyToOne
        @JoinColumn(
                name = "org_id",
                foreignKey = ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
        val org: Org,
        val createAt: LocalDateTime
)