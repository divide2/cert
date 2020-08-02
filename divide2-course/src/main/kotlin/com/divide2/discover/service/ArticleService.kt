package com.divide2.discover.service

import com.divide2.core.service.IService
import com.divide2.discover.dto.ArticleDTO
import com.divide2.discover.model.Article
import com.divide2.discover.repository.ArticleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleService : IService<Article, Int, ArticleRepository> {
    fun add(t: ArticleDTO)

    fun update(t: ArticleDTO)

    fun find(pageable: Pageable): Page<Article>
}