package com.divide2.course.service

import com.divide2.core.service.IService
import com.divide2.core.token.AccessToken
import com.divide2.course.controller.*
import com.divide2.course.model.User
import com.divide2.course.repository.UserRepository
import com.divide2.course.vo.LoginVO

/**
 * @author bvvy
 * @date 2019/10/5
 */
interface UserService : IService<User, Int, UserRepository> {

    /**
     * openId获取用户信息
     */
    fun findByOpenId(openId: String): User?

    /**
     * 通过用户名获取用户信息
     */
    fun findByUsername(username: String): User?

    /**
     * 密码登录
     */
    fun loginByPwd(orgLoginDTO: OrgLoginDTO): AccessToken


    /**
     * 找回密码
     */
    fun retrievePwd(retrievePwdDTO: RetrievePwdDTO)

    /**
     * 验证码找回密码
     */
    fun retrievePwd(retrievePwdDTO: RetrievePwdByVerifyDTO)

    fun retrievePwd(newPwd: NewPwd)

    fun getByPhoneNumber(phoneNumber: String):User?
    /**
     * 手机验证码登录
     */
    fun loginByPhone(phoneLogin: PhoneLogin): LoginVO
}
