package com.divide2.course.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * 用户加入的课程
 * @author bvvy
 *
 * @date 2019/10/10
 */
@Entity
@Table(name = "c_user_course")
class UserCourse(
        var courseId: Int,
        var userId: Int,
        var status: String
):BaseModel()