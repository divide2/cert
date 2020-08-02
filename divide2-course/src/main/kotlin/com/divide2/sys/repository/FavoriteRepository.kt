package com.divide2.sys.repository

import com.divide2.course.model.User
import com.divide2.sys.model.Favorite
import com.divide2.sys.model.Org
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteRepository : JpaRepository<Favorite, Int> {

    fun findByUser(user: User): List<Favorite>

    fun findByOrg(org: Org): List<Favorite>
    fun findByUserAndOrg(user: User, org: Org) : Favorite?
}