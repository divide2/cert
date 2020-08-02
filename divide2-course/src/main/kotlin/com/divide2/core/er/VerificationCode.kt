package com.divide2.core.er


import com.aliyuncs.CommonRequest
import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.http.MethodType
import com.aliyuncs.profile.DefaultProfile
import com.divide2.core.properties.AliyunProperties
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * @author bvvy
 * @date 2018/12/3
 */
class VerificationCode(val code: String)

@Component
class VerificationCodeManager(private val aliyunProperties: AliyunProperties,
                              val jsoner: Jsoner) {
    companion object {
        private val verifyCodeMap = ConcurrentHashMap<String, VerificationCode>()
    }

    private fun generate(): VerificationCode {
        val random = Random()
        val code = (random.nextInt(900000) + 100000).toString()
        return VerificationCode(code)
    }

    fun sendSms(phoneNumber: String) {
        val profile = DefaultProfile.getProfile("default", aliyunProperties.accessKeyId, aliyunProperties.accessKeySecret)
        val client = DefaultAcsClient(profile)
        val verificationCode = generate()
        verifyCodeMap[phoneNumber] = verificationCode
        val request = CommonRequest()
        //request.setProtocol(ProtocolType.HTTPS);
        request.method = MethodType.POST
        request.domain = "dysmsapi.aliyuncs.com"
        request.version = "2017-05-25"
        request.action = "SendSms"
        request.putQueryParameter("PhoneNumbers", phoneNumber)
        request.putQueryParameter("SignName", "除以二")
        request.putQueryParameter("TemplateCode", "SMS_160571359")
        request.putQueryParameter("TemplateParam", jsoner.toJson(verificationCode))
        val response = client.getCommonResponse(request)
//        verifyCodeMap[phoneNumber] = VerificationCode("123456")

    }

    fun validate(phoneNumber: String, code: String): Boolean {
        val verificationCode = verifyCodeMap[phoneNumber]
        return verificationCode != null && verificationCode.code == code
    }

    fun remove(phoneNumber: String) {
        verifyCodeMap.remove(phoneNumber)
    }
}

