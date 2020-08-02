package com.divide2.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import java.time.LocalTime
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import java.time.LocalDateTime
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = jacksonObjectMapper()
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        javaTimeModule.addSerializer(LocalDate::class.java, LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        javaTimeModule.addSerializer(LocalTime::class.java, LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")))
        javaTimeModule.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        javaTimeModule.addDeserializer(LocalDate::class.java, LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        javaTimeModule.addDeserializer(LocalTime::class.java, LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")))
        objectMapper.registerModule(javaTimeModule)
        return objectMapper
    }

}
