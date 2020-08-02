package com.divide2.sys.service

import com.divide2.core.service.IService
import com.divide2.sys.dto.AddressDTO
import com.divide2.sys.model.Address
import com.divide2.sys.repository.AddressRepository

interface AddressService : IService<Address, Int, AddressRepository> {

    fun listByUser(userId: Int): List<Address>

    fun add(dto: AddressDTO): Address
    fun update(id: Int, dto: AddressDTO): Address
    fun listCity(): List<String>
}