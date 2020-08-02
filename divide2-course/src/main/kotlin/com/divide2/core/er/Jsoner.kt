package com.divide2.core.er

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author bvvy
 * @date 2018/8/21
 */
@Component
class Jsoner(var objectMapper: ObjectMapper) {

    fun toJson(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

    fun <T> from(json: String, clz: Class<T>): T {
        return objectMapper.readValue(json, clz)
    }
}
