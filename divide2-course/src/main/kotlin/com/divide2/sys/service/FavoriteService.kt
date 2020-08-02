package com.divide2.sys.service

import com.divide2.sys.model.Favorite
import org.springframework.transaction.annotation.Transactional

interface FavoriteService {

    fun findByUser(userId: Int): List<Favorite>

    fun findByOrg(orgId: Int): List<Favorite>

    fun add(userId: Int, orgId: Int)

    fun delete(userId: Int, orgId: Int)

    fun findByUserAndOrg(userId: Int, orgId: Int): Favorite?

    @Transactional
    fun toggle(userId: Int, orgId: Int) {
        if (findByUserAndOrg(userId, orgId) != null) {
            delete(userId, orgId)
        } else {
            add(userId, orgId)
        }
    }
}