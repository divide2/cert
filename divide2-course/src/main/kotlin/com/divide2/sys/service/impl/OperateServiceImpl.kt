package com.divide2.sys.service.impl

import com.divide2.core.service.impl.ServiceImpl
import com.divide2.sys.model.OperateCity
import com.divide2.sys.repository.OperateCityRepository
import com.divide2.sys.service.OperateCityService
import org.springframework.stereotype.Service

@Service
class OperateServiceImpl(
        var operateCityRepository: OperateCityRepository
) : ServiceImpl<OperateCity, Int, OperateCityRepository>(), OperateCityService {
    override fun findAll(): List<OperateCity> {
        return operateCityRepository.findAll()
    }
}