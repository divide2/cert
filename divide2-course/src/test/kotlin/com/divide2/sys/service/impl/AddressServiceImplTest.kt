package com.divide2.sys.service.impl

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class AddressServiceImplTest() {

    @Autowired
    lateinit var addressService: AddressServiceImpl

    @Test
    fun addOperateCity() {

        addressService.addOperateCity("深圳")
    }
}