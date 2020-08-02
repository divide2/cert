package com.divide2.course.service

import com.divide2.core.service.IService
import com.divide2.course.model.Certificate
import com.divide2.course.repository.CertificateRepository

interface CertificateService : IService<Certificate,Int,CertificateRepository>{

    fun listByUser(userId: Int): List<Certificate>

}