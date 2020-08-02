package com.divide2.discover.controller

import com.divide2.discover.dto.ArticleDTO
import com.divide2.discover.model.Article
import com.divide2.discover.service.ArticleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/v1/articles")
class ArticleController(val articleService: ArticleService) {

    @PostMapping
    fun add(@Valid @RequestBody articleDTO: ArticleDTO): ResponseEntity<Any> {
        articleService.add(articleDTO)
        return ResponseEntity.created(URI.create("")).build()
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody articleDTO: ArticleDTO, @PathVariable id: Int): ResponseEntity<Any> {
        articleDTO.id = id
        articleService.update(articleDTO)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun update(@PathVariable id: Int): ResponseEntity<Article> {
        val article = articleService.getById(id)
        return ResponseEntity.ok(article)
    }

    @GetMapping
    fun list(pageable: Pageable): ResponseEntity<Page<Article>> {
        val list = articleService.find(pageable)
        return ResponseEntity.ok(list)
    }
}