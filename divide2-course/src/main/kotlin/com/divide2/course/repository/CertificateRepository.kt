package com.divide2.course.repository

import com.divide2.course.model.Certificate
import org.springframework.data.jpa.repository.JpaRepository

interface CertificateRepository:JpaRepository<Certificate,Int> {


    fun findByCreateUserId(userId: Int):List<Certificate>


}