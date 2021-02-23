import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springDependencyVersion: String by project
val springBootVersion: String by project

plugins {
    base
    id("io.spring.dependency-management")
    kotlin("jvm") apply false
    kotlin("plugin.spring") apply false
    id("org.jmailen.kotlinter")
}

tasks.check {
    dependsOn("installKotlinterPrePushHook")
}

allprojects {
    group = "ag.bushel"
    version = "1.0.0"

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jmailen.kotlinter")
    }

    repositories {
        jcenter()
    }

    val implementation by configurations

    dependencies {
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        }
    }
}

this.loadProperties()

fun loadProperties() {
    val environment = if (hasProperty("env"))
        property("env").toString()
    else "local"

    val configFile = when (environment) {
        "dev", "qa", "uat" -> file("config.remote.groovy")
        else -> file("config.groovy")
    }

    project.extra["config"] = groovy.util.ConfigSlurper(environment).parse(configFile.readText())
}
