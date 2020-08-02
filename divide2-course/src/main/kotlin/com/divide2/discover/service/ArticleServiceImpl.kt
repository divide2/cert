package com.divide2.discover.service

import com.divide2.core.service.impl.ServiceImpl
import com.divide2.discover.dto.ArticleDTO
import com.divide2.discover.model.Article
import com.divide2.discover.repository.ArticleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(val articleRepository: ArticleRepository) : ServiceImpl<Article, Int, ArticleRepository>(), ArticleService {
    override fun add(t: ArticleDTO) {
        val article = Article(
                t.title,
                t.content,
                t.synopsis,
                t.subtitle,
                t.cover,
                t.tags
        )
        add(article)
    }

    override fun update(t: ArticleDTO) {
        val article = this.getById(t.id)
        article.content = t.content
        article.cover = t.cover
        article.subtitle = t.subtitle
        article.synopsis = t.synopsis
        article.tags = t.tags
        article.title = t.title
        this.update(article)
    }

    override fun find(pageable: Pageable): Page<Article> {
        val sort = Sort.by(Sort.Order.desc("createTime"))
        val request = PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)
        return articleRepository.findAll(request)
    }
}