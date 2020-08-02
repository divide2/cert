package com.divide2.course.service.impl

import com.divide2.core.er.Loginer
import com.divide2.core.service.impl.ServiceImpl
import com.divide2.course.model.Certificate
import com.divide2.course.repository.CertificateRepository
import com.divide2.course.service.CertificateService
import org.springframework.stereotype.Service

@Service
class CertificateServiceImpl(
        var certificateRepository: CertificateRepository
) : ServiceImpl<Certificate, Int, CertificateRepository>(), CertificateService {
    override fun listByUser(userId: Int): List<Certificate> {
        return certificateRepository.findByCreateUserId(userId)
    }

    override fun add(t: Certificate): Certificate {
        t.createUserId = Loginer.getId()
        t.updateUserId = Loginer.getId()
        return super.add(t)
    }
}