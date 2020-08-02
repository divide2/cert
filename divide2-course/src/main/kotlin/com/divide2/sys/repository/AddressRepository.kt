package com.divide2.sys.repository

import com.divide2.sys.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AddressRepository : JpaRepository<Address, Int> {


    fun findByOrgIdAndDeleted(userId: Int, deleted: Boolean): List<Address>

    @Query("select distinct city from Address")
    fun listCity(): List<String>
}