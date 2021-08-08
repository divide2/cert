package com.divide2.course.service.impl

import com.divide2.core.er.Loginer
import com.divide2.core.er.VerificationCodeManager
import com.divide2.core.exception.ValidationException
import com.divide2.core.service.impl.ServiceImpl
import com.divide2.core.token.AccessToken
import com.divide2.core.token.StoreUser
import com.divide2.core.token.TokenStore
import com.divide2.course.controller.*
import com.divide2.course.model.User
import com.divide2.course.repository.UserRepository
import com.divide2.course.service.UserService
import com.divide2.course.vo.LoginVO
import com.divide2.exception.BadAuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(var userRepository: UserRepository,
                      var passwordEncoder: PasswordEncoder,
                      var tokenStore: TokenStore,
                      var verificationCodeManager: VerificationCodeManager
//                      var mailSender: MailSender,
//                      var simpleMailMessage: SimpleMailMessage
) : ServiceImpl<User, Int, UserRepository>(), UserService {
    override fun findByOpenId(openId: String): User? {
        return userRepository.findByOpenId(openId)
    }


    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun loginByPwd(orgLoginDTO: OrgLoginDTO): AccessToken {
        val user = findByUsername(orgLoginDTO.username)
        if (user != null && passwordEncoder.matches(orgLoginDTO.password, user.password)) {
            return tokenStore.retrieveToken(StoreUser(user.id))
        }
        throw BadAuthenticationException("bad_authentication")
    }


    override fun retrievePwd(retrievePwdDTO: RetrievePwdDTO) {
//        val email = Loginer.getEmail()
//        val msg = SimpleMailMessage(this.simpleMailMessage)
//        val token = ""
//        msg.setTo(email)
//        msg.text = "请点击下面的的地址重置密码,15分钟有效: ${retrievePwdDTO.origin}?token=$token"
//        this.mailSender.send(msg)
    }

    override fun retrievePwd(retrievePwdDTO: RetrievePwdByVerifyDTO) {
        val validate = verificationCodeManager.validate(retrievePwdDTO.phoneNumber, retrievePwdDTO.verifyCode)
        if (!validate) {
            throw ValidationException("wrong_verify_code")
        }
    }

    override fun retrievePwd(newPwd: NewPwd) {
        val validate = verificationCodeManager.validate(newPwd.phoneNumber, newPwd.verifyCode)
        if (newPwd.password != newPwd.rePassword) {
            throw ValidationException("different_password")
        }
        if (!validate) {
            throw ValidationException("wrong_verify_code")
        }
        val user = this.getByPhoneNumber(newPwd.phoneNumber)
        if (user != null) {
            user.password = passwordEncoder.encode(newPwd.password)
            this.update(user)
        }
    }

    override fun getByPhoneNumber(phoneNumber: String): User? {
        return userRepository.getByPhoneNumber(phoneNumber)
    }

    override fun loginByPhone(phoneLogin: PhoneLogin): LoginVO {

        val validate = verificationCodeManager.validate(phoneLogin.phoneNumber, phoneLogin.verifyCode)
        // 验证码不对 直接退出
        if (!validate) {
            throw ValidationException("wrong_verify_code")
        }
        var user = this.getByPhoneNumber(phoneLogin.phoneNumber)
        //手机号不存在 那就新增一个用户 把手机放进去
        if (user == null) {
            return LoginVO(false, "")
        }
        // 手机号存在 直接返回token
        val accessToken = tokenStore.retrieveToken(StoreUser(user.id))
        return LoginVO(true, accessToken.value)
    }

    override fun loginByWechat(wechatPhone: WechatPhone): LoginVO {
        val phoneNumber = "15680740187"
        val user = this.getByPhoneNumber(phoneNumber)
        //手机号不存在 那就新增一个用户 把手机放进去
        if (user == null) {
            return LoginVO(false, "")
        }
        // 手机号存在 直接返回token
        val accessToken = tokenStore.retrieveToken(StoreUser(user.id))
        return LoginVO(true, accessToken.value)
    }
}
