package com.divide2.course.repository

import com.divide2.course.model.CourseCertificate
import org.springframework.data.jpa.repository.JpaRepository

interface CourseCertificateRepository:JpaRepository<CourseCertificate,Int> {

    fun deleteByCourseIdAndCertificateId(courseId: Int, certificateId: Int)
}