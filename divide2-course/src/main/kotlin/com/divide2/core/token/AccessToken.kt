package com.divide2.core.token

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author bvvy
 * @date 2019/10/6
 */
data class AccessToken(
        @JsonProperty("accessToken")
        var value: String

)