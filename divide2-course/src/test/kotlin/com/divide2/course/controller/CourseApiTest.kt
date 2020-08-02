package com.divide2.course.controller

import com.divide2.core.token.AccessToken
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.client.RestTemplate

//@SpringBootTest
//@RunWith(SpringRunner::class)
class CourseApiTest {

    /*lateinit var restTemplate: RestTemplate

    @Before
    fun init() {

        this.restTemplate = RestTemplate()

    }

    @Test
    fun login() {
        val username = "123"
        val password = "123"
        val params = mapOf(Pair("username", username), Pair("password", password))
        val httpEntity = HttpEntity<Map<String,String>>(params)
        val tokenResp = restTemplate.postForEntity("http://localhost:8080/v1/login/org",httpEntity, AccessToken::class.java)
        println(tokenResp.body)
    }
*/

}
