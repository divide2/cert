package com.divide2.sys.service.impl

import com.divide2.core.er.VerificationCodeManager
import com.divide2.core.exception.ValidationException
import com.divide2.core.service.impl.ServiceImpl
import com.divide2.core.token.AccessToken
import com.divide2.core.token.StoreUser
import com.divide2.core.token.TokenStore
import com.divide2.course.controller.OrgLoginDTO
import com.divide2.course.controller.RegDTO
import com.divide2.course.model.AUDITING
import com.divide2.course.model.User
import com.divide2.exception.BadAuthenticationException
import com.divide2.sys.model.Org
import com.divide2.sys.repository.OrgRepository
import com.divide2.sys.service.OrgService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OrgServiceImpl(
        var orgRepository: OrgRepository,
        var passwordEncoder: PasswordEncoder,
        var tokenStore: TokenStore,
        var verificationCodeManager: VerificationCodeManager
) : ServiceImpl<Org, Int, OrgRepository>(), OrgService {
    override fun loginByPwd(orgLoginDTO: OrgLoginDTO): AccessToken {
        val org = getByName(orgLoginDTO.username)
        if (org != null && passwordEncoder.matches(orgLoginDTO.password, org.password)) {
            return tokenStore.retrieveToken(StoreUser(org.id))
        }
        throw BadAuthenticationException("bad_authentication")
    }

    override fun getByName(name: String): Org? {
        return orgRepository.getByName(name)
    }

    override fun reg(regDTO: RegDTO) {
        val valid = verificationCodeManager.validate(regDTO.contactWay, regDTO.verifyCode);
        if (!valid) {
            throw ValidationException("wrong_verify_code")
        }
        this.add(Org(
                name = regDTO.username,
                password = passwordEncoder.encode(regDTO.password),
                license = regDTO.license,
                status = AUDITING,
                contactUser = regDTO.contactUser,
                contactWay = regDTO.contactWay,
                address = regDTO.address,
                email = regDTO.email
        ))
    }

}
