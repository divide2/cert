package com.divide2.sys.service.impl

import com.divide2.course.repository.UserRepository
import com.divide2.sys.model.Favorite
import com.divide2.sys.repository.FavoriteRepository
import com.divide2.sys.repository.OrgRepository
import com.divide2.sys.service.FavoriteService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FavoriteServiceImpl(
        val favoriteRepository: FavoriteRepository,
        val userRepository: UserRepository,
        val orgRepository: OrgRepository
) : FavoriteService {
    override fun findByUser(userId: Int): List<Favorite> {
        val user = userRepository.getOne(userId)
        val favorites = favoriteRepository.findByUser(user)
        favorites.forEach { it.org.isFavorite = true }
        return favorites
    }

    override fun findByOrg(orgId: Int): List<Favorite> {
        val org = orgRepository.getOne(orgId)
        return favoriteRepository.findByOrg(org)
    }

    override fun add(userId: Int, orgId: Int) {
        val user = userRepository.getOne(userId)
        val org = orgRepository.getOne(orgId)
        val favorite = Favorite(0, user, org, LocalDateTime.now())
        favoriteRepository.save(favorite)
    }

    override fun delete(userId: Int, orgId: Int) {
        val favorite = findByUserAndOrg(userId, orgId)
        if (favorite != null) {
            favoriteRepository.delete(favorite)
        }
    }

    override fun findByUserAndOrg(userId: Int, orgId: Int): Favorite? {
        val user = userRepository.getOne(userId)
        val org = orgRepository.getOne(orgId)
        return favoriteRepository.findByUserAndOrg(user, org)
    }

}