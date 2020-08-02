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
@Table(name = "c_course_certificate")
class CourseCertificate (
        var courseId: Int,
        var certificateId: Int
):BaseModel()