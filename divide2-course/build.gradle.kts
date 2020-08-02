import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.Database
import org.jooq.meta.jaxb.Generator
import org.jooq.meta.jaxb.Jdbc
import org.jooq.meta.jaxb.Target

group = "com.divide2"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

sourceSets {
    main {
        java {
            srcDir("build/generate-source")
        }
    }
}

dependencies {
    implementation(project(":divide2-common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-mail")
//    implementation("com.sun.mail:javax.mail")
    implementation("org.jooq:jooq-meta:3.13.2")
    implementation("org.jooq:jooq-codegen:3.13.2")
    implementation("com.nimbusds:nimbus-jose-jwt:8.2.1")
    implementation("com.belerweb:pinyin4j:2.5.1")

    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("com.vladmihalcea:hibernate-types-52:2.2.3")
    implementation("org.hibernate.validator:hibernate-validator:6.1.5.Final")
    implementation("com.aliyun.oss:aliyun-sdk-oss:2.8.3")
    implementation("com.aliyun:aliyun-java-sdk-core:4.1.0")
    implementation("commons-io:commons-io:2.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2:1.4.200")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jooq:jooq-codegen:3.13.2")
        classpath("com.h2database:h2:1.4.200")
        classpath("org.postgresql:postgresql:42.2.14")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
tasks.create("generate") {
    group = "hard"
    doFirst {
        val configurations = org.jooq.meta.jaxb.Configuration()
                .withJdbc(
                        Jdbc()
                                .withUrl("jdbc:postgresql://120.77.153.225:5432/mealcord")
                                .withPassword("bvvyeh288")
                                .withDriver("org.postgresql.Driver")
                                .withUsername("postgres")
                ).withGenerator(Generator()
                        .withDatabase(
                                Database()
                                        .withName("org.jooq.meta.postgres.PostgresDatabase")
                                        .withInputSchema("public")
                                        .withIncludes(".*")
                        )
                        .withTarget(
                                Target()
                                        .withPackageName("com.divide2.jooq")
                                        .withDirectory("build/generate-source")

                        )
                )
        try  {
            org.jooq.codegen.GenerationTool.generate(configurations)
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

}



