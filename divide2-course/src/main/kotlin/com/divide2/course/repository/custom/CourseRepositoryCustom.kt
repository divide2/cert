package com.divide2.course.repository.custom

import com.divide2.course.model.User
import com.divide2.course.query.CourseQuery
import com.divide2.course.vo.CourseVO
import com.divide2.course.vo.UserVO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CourseRepositoryCustom {

    fun findByOrgId(userId: Int, pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO>

    fun findJoined(userId: Int, pageable: Pageable,courseQuery: CourseQuery): Page<CourseVO>

    fun get(id: Int) : CourseVO?

    fun listCourseUsers(courseId: Int): List<UserVO>

    fun findAll(pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO>

}
