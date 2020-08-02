package com.divide2.config

import com.fasterxml.classmate.TypeResolver
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.*
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SecurityConfiguration
import springfox.documentation.swagger.web.SecurityConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2

import java.util.Collections.singletonList
import springfox.documentation.builders.PathSelectors.ant

/**
 * @author bvvy
 * swagger2 config
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Autowired
    private val typeResolver: TypeResolver? = null

    @Bean
    fun petApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api::class.java))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/")
                .directModelSubstitute(Char::class.java, String::class.java)
                .genericModelSubstitutes(ResponseEntity::class.java)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, listOf(ResponseMessageBuilder().code(409).message("{code: 错误问题}").build())
                )
                .ignoredParameterTypes(Pageable::class.java)
                .securitySchemes(listOf(oauth()))
                .securityContexts(listOf(securityContext()))//                .enableUrlTemplating(true)
        //               .additionalModels(typeResolver.resolve(AdditionalModel.class))
    }

    private fun apiInfo(): ApiInfo {

        return ApiInfoBuilder()
                .description("小影圈接口文档")
                .title("小影圈接口文档")
                .license("Apache License Version 2.0")
                .version("0.0.1")
                .termsOfServiceUrl("/")
                .build()
    }

    fun securityContext(): SecurityContext {
        val readScope = AuthorizationScope("webclient", "webclient")
        val scopes = arrayOfNulls<AuthorizationScope>(1)
        scopes[0] = readScope
        val securityReference = SecurityReference.builder()
                .reference("OAuth2")
                .scopes(scopes)
                .build()

        return SecurityContext.builder()
                .securityReferences(listOf(securityReference))
                .forPaths(ant("/v1/**"))
                .build()
    }


    @Bean
    fun oauth(): SecurityScheme {
        return OAuthBuilder()
                .name("OAuth2")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build()
    }

    private fun grantTypes(): List<GrantType> {
        val grantType = ResourceOwnerPasswordCredentialsGrant("/v1/login/org")
        return listOf<GrantType>(grantType)
    }

    private fun scopes(): List<AuthorizationScope> {
        return listOf(AuthorizationScope("webclient", "登录"))
    }

    @Bean
    fun securityInfo(): SecurityConfiguration {
        return SecurityConfigurationBuilder.builder()
                .clientId("aiNzsAXE8tkOFJN6")
                .clientSecret("12345678")
                .realm("realm")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .appName("xyq")
                .build()
    }

}