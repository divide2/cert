package com.divide2.course.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * 行业
 */
@Entity
@Table(name = "c_profession")
data class Profession(
        @Id
        @GeneratedValue
        var id:Int,
        var name: String
)