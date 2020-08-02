package com.divide2.exception

class BadAuthenticationException(override val message: String?)
    : RuntimeException()