package com.divide2.core.service.impl

import com.divide2.core.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author bvvy
 * @date 2019/10/1
 */
open class ServiceImpl<T, ID, R : JpaRepository<T, ID>> : IService<T, ID, R> {

    @Autowired
    lateinit var r: R;


    override fun add(t: T): T {
       return r.save(t)
    }

    override fun update(t: T): T {
        return r.save(t)
    }

    override fun deleteById(id: ID) {
        r.deleteById(id)
    }

    override fun list(): List<T> {
        return r.findAll()
    }

    override fun page(pageable: Pageable): Page<T> {
        return r.findAll(pageable)
    }

    override fun getById(id: ID): T {
        return r.getOne(id)
    }
}