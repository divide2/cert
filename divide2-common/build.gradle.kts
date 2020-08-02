import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//
plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

//group = "com.divide2.cert"
//version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}