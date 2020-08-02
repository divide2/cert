package com.divide2.course.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author bvvy
 * @date 2019/10/10
 */
@Entity
@Table(name = "c_teacher")
class Teacher(
        var name: String = "",
        var image: String = "",
        var relateUser: Int = 0,
        var description: String = ""
): BaseModel()
