package com.divide2.course.controller

import com.divide2.course.query.CourseQuery
import com.divide2.course.service.CourseService
import com.divide2.course.vo.CourseVO
import io.swagger.annotations.Api
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Api(tags = ["课程搜索"])
@RestController
@RequestMapping("/v1/courses")
class CourseController(
        var courseService: CourseService
) {
    @GetMapping
    fun findCourse(pageable: Pageable, courseQuery: CourseQuery): ResponseEntity<Page<CourseVO>> {
        val courses =  courseService.findAll(pageable, courseQuery)
        return ResponseEntity.ok(courses)
    }


}
