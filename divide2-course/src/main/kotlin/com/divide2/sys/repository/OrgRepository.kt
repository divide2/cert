package com.divide2.sys.repository

import com.divide2.sys.model.Org
import org.springframework.data.jpa.repository.JpaRepository

interface OrgRepository :JpaRepository<Org, Int> {

    fun getByName(name: String): Org?
}