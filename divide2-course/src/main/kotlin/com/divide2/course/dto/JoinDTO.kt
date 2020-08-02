package com.divide2.course.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class JoinDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        var courseId: Int,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        var userId: Int
)
