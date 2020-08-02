package com.divide2.sys.service

import com.divide2.core.service.IService
import com.divide2.sys.dto.AddressDTO
import com.divide2.sys.model.Address
import com.divide2.sys.model.OperateCity
import com.divide2.sys.repository.AddressRepository
import com.divide2.sys.repository.OperateCityRepository

interface OperateCityService : IService<OperateCity, Int, OperateCityRepository> {
    fun findAll(): List<OperateCity>

}