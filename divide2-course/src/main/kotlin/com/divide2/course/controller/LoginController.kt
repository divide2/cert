package com.divide2.course.controller

import com.divide2.core.er.Jsoner
import com.divide2.core.er.VerificationCodeManager
import com.divide2.core.exception.ValidationException
import com.divide2.core.properties.WechatProp
import com.divide2.core.token.AccessToken
import com.divide2.core.token.TokenStore
import com.divide2.course.service.UserService
import com.divide2.course.vo.LoginVO
import com.divide2.sys.service.OrgService
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Email

/**
 * @author bvvy
 * @date 2019/10/1
 */
@RestController
@RequestMapping("/v1")
@Api(tags=["登录相关"])
class LoginController(
        var userService: UserService,
        var wechatProp: WechatProp,
        var jsoner: Jsoner,
        var tokenStore: TokenStore,
        var orgService: OrgService,
        var verificationCodeManager: VerificationCodeManager
) {

   /* @PostMapping("/login/wechat")
    @ApiOperation("微信手机号登录")
    fun wechatLogin(@RequestBody @Valid wechatPhone: WechatPhone): AccessToken {
        val iv = wechatPhone.iv
    }*/

    @ApiOperation("手机号登录")
    @PostMapping("/login/phone")
    fun phoneLogin(@RequestBody @Valid phoneLogin: PhoneLogin): ResponseEntity<LoginVO> {
        val loginVO = userService.loginByPhone(phoneLogin)
        return ResponseEntity.ok(loginVO)
    }

    @ApiOperation("发送平台外用户验证码")
    @PostMapping("/outside/verify/code")
    fun sendOutsideSmd(@RequestBody @Valid phone: Phone):ResponseEntity<Any> {
        verificationCodeManager.sendSms(phone.phoneNumber)
        return ResponseEntity.ok().build()
    }

    @ApiOperation("发送已有用户验证码")
    @PostMapping("/inside/verify/code")
    fun sendInsideSmd(@RequestBody @Valid phone: Phone):ResponseEntity<Any> {
        val user = userService.getByPhoneNumber(phone.phoneNumber)
        if (user == null) {
            throw ValidationException("not_exist_phone")
        }
        verificationCodeManager.sendSms(phone.phoneNumber)
        return ResponseEntity.ok().build()
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") accessToken: String):ResponseEntity<Any> {
        tokenStore.revokeToken(AccessToken(accessToken))
        return ResponseEntity.noContent().build()
    }

    @ApiOperation("组织登录")
    @PostMapping("/login/org")
    fun orgLogin(@RequestBody @Valid orgLoginDTO: OrgLoginDTO): ResponseEntity<AccessToken> {
        return ResponseEntity.ok(orgService.loginByPwd(orgLoginDTO))
    }


    @PostMapping("/reg")
    @ApiOperation("注册")
    fun reg(@RequestBody @Valid regDTO: RegDTO): ResponseEntity<RegDTO> {
        orgService.reg(regDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(regDTO)
    }

    @PostMapping("/pwd/retrieve")
    @ApiOperation("找回密码")
    fun findPwd(@RequestBody @Valid retrievePwdDTO: RetrievePwdDTO):ResponseEntity<RetrievePwdDTO>  {
        userService.retrievePwd(retrievePwdDTO)
        return ResponseEntity.ok(retrievePwdDTO)
    }

    @PostMapping("/pwd/verify/retrieve")
    @ApiOperation("验证码找回密码")
    fun findPwd(@RequestBody @Valid retrievePwdDTO: RetrievePwdByVerifyDTO):ResponseEntity<Any>  {
        userService.retrievePwd(retrievePwdDTO)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/pwd/new")
    fun newPwd(@RequestBody @Valid newPwd: NewPwd) :ResponseEntity<Any> {
        userService.retrievePwd(newPwd)
        return ResponseEntity.ok().build()
    }

}

class NewPwd(
        var phoneNumber: String,
        var verifyCode: String,
        var password: String,
        var rePassword: String
)

class Phone(var phoneNumber: String)

data class PhoneLogin(var phoneNumber: String,
                      var verifyCode: String)
data class RetrievePwdByVerifyDTO(
        var phoneNumber: String,
        var verifyCode: String
)
data class RetrievePwdDTO(
        var email: String,
        var origin: String
)

data class RegDTO(var username: String,
                  var password: String,
                  var license: String,
                  var address: String,
                  var contactUser: String,
                  var contactWay: String,
                  var verifyCode: String,
                  @field:Email
                  var email: String)

data class OrgLoginDTO(var username: String, var password: String)

data class LoginUserDTO(
        val phoneNumber: String,
        val nickName: String,
        val avatarUrl: String,
        val city: String,
        val country: String,
        val gender: String,
        val language: String,
        val province: String
)

class WechatPhone(
        var iv: String,
        var encryptedData: String
)

class WechatLoginResp {
    var openid: String = ""
    @JsonProperty("session_key")
    var sessionKey: String = ""
    var errcode: Int = 0
    var errmsg: String = ""
    var unionid: String = ""

    @JsonIgnore
    fun isSuccess():Boolean {
        return errcode == 0
    }
}
