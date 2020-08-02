package com.divide2.common.model

import com.divide2.course.model.BaseModel
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "com_slider")
data class Slider(
        var link: String,
        var image: String,
        //权重 越大越靠前
        var weight: Int
) : BaseModel()