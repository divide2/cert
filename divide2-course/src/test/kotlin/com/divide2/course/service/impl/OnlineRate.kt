package com.divide2.course.service.impl

import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.nio.file.Files
import java.nio.file.Paths

class OnlineRate {

}

fun main(args: Array<String>) {
    val resource = ClassPathResource("a.txt")

    val headers = HttpHeaders()
    val token =   "eyJhbGciOiJIUzI1NiJ9.eyJvcGVyYXRvcl90b2tlbiI6IkZJQUZLWFpBSEpCSEEiLCJvcGVyYXRvcl9sb2dpbl9jb3VudCI6NzYsIm9wZXJhdG9yX2FwcF9sb2dpbl9jb3VudCI6MCwibG9naW5fZnJvbV9tb2JpbGVfYXBwIjpmYWxzZX0.ll_QJlBmFMB2qvfaGcQ1_QZZGvUu8qP7hbXiMgcgSFw"
    headers["Authorization"] = "Bearer $token"
    val httpEntity  =HttpEntity<Any>(headers)
    val restTemplate = RestTemplate()
    Files.readAllLines(Paths.get(resource.uri)).parallelStream().forEach {
        val uri = "https://web-production.lime.bike/api/op/v1/bicycles/get_core_data_by_imei?imei=$it"
        try {
            val resp = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String::class.java)

        } catch (e: HttpClientErrorException) {
            println(e.message)
        }
    }
}