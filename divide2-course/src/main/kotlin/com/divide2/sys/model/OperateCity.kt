package com.divide2.sys.model

import com.divide2.course.model.BaseModel
import javax.persistence.Entity
import javax.persistence.Table

@Table(name="sys_operate_city")
@Entity
class OperateCity(
        val name: String,
        val pinyin: String,
        val firstLetter: String
): BaseModel()
