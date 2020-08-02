package com.divide2.course.service

import com.divide2.core.service.IService
import com.divide2.course.dto.CourseDTO
import com.divide2.course.dto.JoinDTO
import com.divide2.course.model.Course
import com.divide2.course.model.User
import com.divide2.course.query.CourseQuery
import com.divide2.course.repository.CourseRepository
import com.divide2.course.vo.CourseVO
import com.divide2.course.vo.UserVO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * @author bvvy
 * @date 2019/10/1
 */
interface CourseService : IService<Course, Int, CourseRepository> {

    /**
     * 查询创建的课程
     */
    fun findByOrgId(userId: Int, pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO>

    /**
     * 加入的课程
     */
    fun findJoined(userId: Int, pageable: Pageable,courseQuery: CourseQuery): Page<CourseVO>


    /**
     * 添加课程
     */
    fun add(courseDTO: CourseDTO): Course

    /**
     * 修改课程
     */
    fun update(courseDTO: CourseDTO): Course

    /**
     * 加入课程
     */
    fun join(join: JoinDTO)

    /**
     * 查询单个课程
     */
    fun get(id: Int): CourseVO

    /**
     * 查询课程下的学生
     */
    fun listCourseUsers(courseId: Int): List<UserVO>

    /**
     * 全部课程查询
     */
    fun findAll(pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO>

    /**
     * 结束报名
     */
    fun finish(courseId: Int)

    /**
     * 发布
     */
    fun publish(courseId: Int)

    fun findByOrg(id: Int, pageable: Pageable): Page<CourseVO>

}