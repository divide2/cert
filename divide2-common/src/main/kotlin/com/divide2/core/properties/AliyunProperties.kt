package com.divide2.core.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author bvvy
 * @date 2018/7/17
 */
@ConfigurationProperties(prefix = "aliyun")
@Component
class AliyunProperties {
    lateinit var accessKeyId: String
    lateinit var accessKeySecret: String
    var oss: Oss? = null

}

class Oss {
    var endPoint: String? = null
    var bucketName: String? = null
    var fileHost: String? = null
}
