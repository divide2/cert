package com.divide2.course.repository

import com.divide2.course.model.Course
import com.divide2.course.repository.custom.CourseRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author bvvy
 * @date 2019/10/1
 */
interface CourseRepository : CourseRepositoryCustom, JpaRepository<Course, Int> {


}