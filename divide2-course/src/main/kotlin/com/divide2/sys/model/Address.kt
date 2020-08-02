package com.divide2.sys.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "sys_address")
data class Address(
        var province: String,
        var city: String,
        var address: String,
        var district: String,
        var longitude: Double,
        var latitude: Double,
        var detail: String,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        var id: Int = 0,
        var orgId: Int = 0,
        var createAt: LocalDateTime = LocalDateTime.now(),
        var updateAt: LocalDateTime = LocalDateTime.now(),
        @Type(type = "yes_no")
        var deleted: Boolean
)