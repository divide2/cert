package com.divide2.course.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author bvvy
 * @date 2019/10/10
 */
data class CourseDTO(

        var id: Int,

        @field:NotEmpty
        var name: String,

        var startTime: LocalDate,

        var endTime: LocalDate,

        var professionId: Int,

        var certificateId: Int = 0,

        var description: String,

        var addressId: Int,

        var arrangement: String,

        @field:NotNull
        var price: BigDecimal,

        var images: Array<String>,

        var details: String,
        /**
         * 招收人数
         */
        var capacity: Int = 0

)