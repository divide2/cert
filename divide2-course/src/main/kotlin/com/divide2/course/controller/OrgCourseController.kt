package com.divide2.course.controller

import com.divide2.core.er.Loginer
import com.divide2.course.dto.CourseDTO
import com.divide2.course.model.Course
import com.divide2.course.query.CourseQuery
import com.divide2.course.service.CourseService
import com.divide2.course.vo.CourseVO
import com.divide2.course.vo.UserVO
import com.divide2.sys.model.Address
import com.divide2.sys.service.AddressService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

/**
 * @author bvvy
 * @date 2019/10/1
 */
@Api(tags = ["机构课程"])
@RestController
@RequestMapping("/v1/org")
class OrgCourseController(
        var courseService: CourseService,
        var addressService: AddressService
) {

    @PostMapping("/courses")
    @ApiOperation("添加课程")
    fun addCourse(@RequestBody @Valid courseDTO: CourseDTO): ResponseEntity<Course> {
        val course = courseService.add(courseDTO)
        return ResponseEntity.created(URI("")).body(course)
    }

    @GetMapping("/addresses")
    fun userAddress(): ResponseEntity<List<Address>> {
        val address = addressService.listByUser(Loginer.getId())
        return ResponseEntity.ok(address)
    }

    @ApiOperation("课程学生")
    @GetMapping("/courses/{courseId}/students")
    fun listCourseUsers(@PathVariable courseId: Int): ResponseEntity<List<UserVO>> {
        val users = courseService.listCourseUsers(courseId)
        return ResponseEntity.ok(users)
    }

    @ApiOperation("机构全部课程")
    @GetMapping("/courses")
    fun findOrgCourse(courseQuery: CourseQuery, pageable: Pageable): ResponseEntity<Page<CourseVO>> {
        val course = courseService.findByOrgId(Loginer.getId(), pageable, courseQuery)
        return ResponseEntity.ok(course)
    }

    @ApiOperation("结束报名")
    @PutMapping("/courses/{courseId}/finish")
    fun unshelve(@PathVariable courseId: Int): ResponseEntity<Any> {
        courseService.finish(courseId)
        return ResponseEntity.ok().build()
    }

    @ApiOperation("上架")
    @PutMapping("/courses/{courseId}/publish")
    fun shelve(@PathVariable courseId: Int): ResponseEntity<Any> {
        courseService.publish(courseId)
        return ResponseEntity.ok().build()
    }

    @ApiOperation("删除")
    @DeleteMapping("/courses/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        courseService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation("修改单个课程")
    @PutMapping("/courses/{id}")
    fun update(@RequestBody courseDTO: CourseDTO, @PathVariable id: Int): ResponseEntity<Course> {

        val update = courseService.update(courseDTO)
        return ResponseEntity.ok(update)
    }

    @ApiOperation("获取单个课程")
    @GetMapping("/courses/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<CourseVO> {
        val course = courseService.get(id)
        return ResponseEntity.ok(course)
    }

}