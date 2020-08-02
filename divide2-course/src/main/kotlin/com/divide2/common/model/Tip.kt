package com.divide2.common.model

import com.divide2.course.model.BaseModel
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "com_tip")
data class Tip(
        var message: String,
        var status: String,
        //权重 越大越靠前
        var weight: Int
) : BaseModel()