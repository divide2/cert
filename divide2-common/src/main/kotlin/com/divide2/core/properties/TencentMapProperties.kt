package com.divide2.core.properties


import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author bvvy
 * @date 2018/7/17
 */
@ConfigurationProperties(prefix = "tencent.map")
@Component
class TencentMapProperties {
    lateinit var key: String
    lateinit var secret: String
}
