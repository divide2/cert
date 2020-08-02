package com.divide2.core.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author bvvy
 * @date 2019/10/5
 */
@ConfigurationProperties(prefix = "wechat")
@Component
class WechatProp {
    lateinit var appId: String
    lateinit var appSecret: String
}
