package com.divide2.sys.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class AddressDTO(
        @field:NotNull
        var longitude: Double,
        @field:NotNull
        var latitude: Double,
        @field:NotBlank
        var detail: String,
        @field:NotBlank
        var address: String

)