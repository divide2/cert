package com.divide2.sys.service

import com.divide2.core.service.IService
import com.divide2.core.token.AccessToken
import com.divide2.course.controller.OrgLoginDTO
import com.divide2.course.controller.RegDTO
import com.divide2.sys.model.Org
import com.divide2.sys.repository.OrgRepository

interface OrgService : IService<Org, Int, OrgRepository> {


    fun loginByPwd(orgLoginDTO: OrgLoginDTO): AccessToken

    fun getByName(name: String): Org?

    /**
     * 注册
     */
    fun reg(regDTO: RegDTO)
}