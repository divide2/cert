package com.divide2.discover.model

import com.divide2.course.model.BaseModel
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "dis_article")
data class Article(
        var title: String,
        var content: String,
        var synopsis: String,
        var subtitle: String,
        var cover: String,
        var tags: String
) : BaseModel()