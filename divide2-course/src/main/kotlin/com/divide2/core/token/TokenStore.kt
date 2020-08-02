package com.divide2.core.token


/**
 * @author bvvy
 * @date 2019/10/6
 */
interface TokenStore {

    fun retrieveToken(content: StoreUser): AccessToken

    fun revokeToken(accessToken: AccessToken)

    fun extract(accessToken: AccessToken): StoreUser?
}