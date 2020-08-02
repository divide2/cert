package com.divide2.course.repository

import com.divide2.course.model.UserCourse
import com.divide2.course.vo.CourseVO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserCourseRepository : JpaRepository<UserCourse, Int> {

    fun getByUserIdAndCourseId(userId: Int, courseId: Int): UserCourse?

}