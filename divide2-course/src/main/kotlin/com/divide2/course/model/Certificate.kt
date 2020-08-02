package com.divide2.course.model

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

/**
 * @author bvvy
 * @date 2019/10/10
 */
@Entity
@Table(name = "c_certificate")
class Certificate(
        @NotEmpty
        var name: String,
        @NotEmpty
        var image: String,
        @NotEmpty
        var licensor: String

) : BaseModel()
