package com.divide2.course.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.vladmihalcea.hibernate.type.array.IntArrayType
import com.vladmihalcea.hibernate.type.array.StringArrayType
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author bvvy
 * @date 2019/10/1
 */
@Entity
@Table(name = "c_course")
@TypeDefs(
        TypeDef(name = "string-array", typeClass = StringArrayType::class)
)
class Course(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        var id: Int = 0,

        var orgId: Int = 0,

        var name: String,

        var startTime: LocalDate,

        var endTime: LocalDate,

        var professionId: Int = 0,

        var description: String,

        var arrangement: String,

        var price: BigDecimal,

        var addressId: Int,

        var hasCert: Boolean,

        @Type(type = "string-array")
        @Column(name = "images", columnDefinition = "text[]")
        var images: Array<String>,

        @Column(columnDefinition = "text")
        var details: String,

        var status: String,

        var capacity: Int,
        /**
         * 招收人数
         */
        var enrolment: Int,

        /**
         * 审核信息
         */
        var auditMessage: String,
        var createAt: LocalDateTime = LocalDateTime.now(),
        var updateAt: LocalDateTime = LocalDateTime.now()
)