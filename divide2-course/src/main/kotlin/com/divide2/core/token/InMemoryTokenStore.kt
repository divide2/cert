package com.divide2.core.token

import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * @author bvvy
 * @date 2019/10/6
 */
class InMemoryTokenStore : TokenStore {
    companion object {
        val ACCESS_TOKEN_STORE = ConcurrentHashMap<AccessToken, StoreUser>()
    }

    override fun retrieveToken(content: StoreUser): AccessToken {
        val accessToken = AccessToken(UUID.randomUUID().toString())
        ACCESS_TOKEN_STORE[accessToken] = content
        return accessToken
    }

    override fun revokeToken(accessToken: AccessToken) {
        ACCESS_TOKEN_STORE.remove(accessToken)
    }

    override fun extract(accessToken: AccessToken): StoreUser? {
        return ACCESS_TOKEN_STORE[accessToken]
    }
}
