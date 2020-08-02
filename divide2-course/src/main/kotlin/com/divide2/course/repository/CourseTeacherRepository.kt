package com.divide2.course.repository

import com.divide2.course.model.CourseTeacher
import org.springframework.data.jpa.repository.JpaRepository

interface CourseTeacherRepository:JpaRepository<CourseTeacher,Int> {
}