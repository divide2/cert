//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//version = "0.0.1-SNAPSHOT"

plugins {

    kotlin("jvm") version "1.3.72"
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
}


allprojects {
    repositories {
        mavenCentral()
    }

    dependencies {
    }

}

