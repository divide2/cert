package com.divide2.discover.repository

import com.divide2.discover.model.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Int>