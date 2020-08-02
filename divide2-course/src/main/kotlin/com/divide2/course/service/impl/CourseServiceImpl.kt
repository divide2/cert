package com.divide2.course.service.impl

import com.divide2.core.er.Loginer
import com.divide2.core.exception.NotFoundException
import com.divide2.core.exception.ValidationException
import com.divide2.core.service.impl.ServiceImpl
import com.divide2.course.dto.CourseDTO
import com.divide2.course.dto.JoinDTO
import com.divide2.course.model.*
import com.divide2.course.query.CourseQuery
import com.divide2.course.repository.CertificateRepository
import com.divide2.course.repository.CourseCertificateRepository
import com.divide2.course.repository.CourseRepository
import com.divide2.course.repository.UserCourseRepository
import com.divide2.course.service.CourseService
import com.divide2.course.vo.CourseVO
import com.divide2.course.vo.UserVO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * @author bvvy
 * @date 2019/10/1
 */
@Service
class CourseServiceImpl(var courseRepository: CourseRepository,
                        var userCourseRepository: UserCourseRepository,
                        var courseCertificateRepository: CourseCertificateRepository,
                        var certificateRepository: CertificateRepository)
    : ServiceImpl<Course, Int, CourseRepository>(), CourseService {
    override fun findByOrgId(orgId: Int, pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO> {
        val courses = courseRepository.findByOrgId(orgId, pageable, courseQuery)
//        val professionIds = courses.map { it.professionId }.content
//        println(professionIds)
        return courses
    }

    override fun findJoined(userId: Int, pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO> {
        return courseRepository.findJoined(userId, pageable, courseQuery)
    }


    override fun add(courseDTO: CourseDTO): Course {
        val course = Course(
                name = courseDTO.name,
                startTime = courseDTO.startTime,
                endTime = courseDTO.endTime,
                professionId = courseDTO.professionId,
                description = courseDTO.description,
                arrangement = courseDTO.arrangement,
                price = courseDTO.price,
                images = courseDTO.images,
                details = courseDTO.details,
                addressId = courseDTO.addressId,
                hasCert = courseDTO.certificateId != 0,
                status = AUDITING,
                capacity = courseDTO.capacity,
                enrolment = 0,
                auditMessage = ""
        )
        course.orgId = Loginer.getId()
        this.add(course)
        val courseCertificate = CourseCertificate(course.id, courseDTO.certificateId)
        courseCertificateRepository.save(courseCertificate)
        return course
    }

    @Transactional
    override fun update(courseDTO: CourseDTO): Course {
        val course = this.getById(courseDTO.id)

        course.name = courseDTO.name
        course.startTime = courseDTO.startTime
        course.endTime = courseDTO.endTime
        course.professionId = courseDTO.professionId
        course.description = courseDTO.description
        course.addressId = courseDTO.addressId
        course.hasCert = courseDTO.certificateId != 0
//        course.streets = courseDTO.streets
//        course.longitude = courseDTO.longitude
//        course.latitude = courseDTO.latitude
        course.arrangement = courseDTO.arrangement
        course.price = courseDTO.price
        course.status = AUDITING
        course.images = courseDTO.images
        course.details = courseDTO.details
        course.capacity = courseDTO.capacity
        course.updateAt = LocalDateTime.now()

        courseCertificateRepository.deleteByCourseIdAndCertificateId(course.id, courseDTO.certificateId)
        val courseCertificate = CourseCertificate(course.id, courseDTO.certificateId)
        courseCertificateRepository.save(courseCertificate)
        return this.update(course)

    }

    override fun join(join: JoinDTO) {
        var uc = userCourseRepository.getByUserIdAndCourseId(join.userId, join.courseId)
        if (uc == null) {
            val userCourse = UserCourse(join.courseId, join.userId, "JOINED")
            userCourseRepository.save(userCourse)
            val course = this.getById(join.courseId)
            course.enrolment++
            if (course.enrolment == course.capacity) {
                course.status = FINISHED
            }
            this.update(course)
        } else {
            throw ValidationException("already_joined")
        }
    }

    override fun get(id: Int): CourseVO {
        return courseRepository.get(id) ?: throw NotFoundException()
    }

    override fun listCourseUsers(courseId: Int): List<UserVO> {
        return courseRepository.listCourseUsers(courseId)
    }

    override fun findAll(pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO> {
        return courseRepository.findAll(pageable, courseQuery)
    }

    override fun findByOrg(id: Int, pageable: Pageable): Page<CourseVO> {
        val query = CourseQuery(
                null,
                null,
                null,
                PUBLISHED,
                "",
                ""
        )
        return this.findByOrgId(id, pageable, query)
    }

    override fun finish(courseId: Int) {
        val course = this.getById(courseId)
        course.status = FINISHED
        this.update(course)
    }

    override fun publish(courseId: Int) {
        val course = this.getById(courseId)
        course.status = PUBLISHED
        this.update(course)
    }

}