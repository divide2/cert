package com.divide2.sys.repository

import com.divide2.sys.model.OperateCity
import org.springframework.data.jpa.repository.JpaRepository

interface OperateCityRepository : JpaRepository<OperateCity, Int> {
    /**
     * 通过名称
     */
    fun findByName(city: String): OperateCity?
}
