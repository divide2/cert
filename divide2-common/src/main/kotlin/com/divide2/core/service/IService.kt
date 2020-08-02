package com.divide2.core.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author bvvy
 * @date 2019/10/1
 */
interface IService<T, ID, R : JpaRepository<T, ID>> {


    fun add(t: T): T;

    fun update(t: T): T

    fun deleteById(id: ID)

    fun list(): List<T>

    fun page(pageable: Pageable): Page<T>
    
    fun getById(id: ID) : T
}