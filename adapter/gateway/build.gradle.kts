description = "adapter.gateway"

val mapStructVersion: String by project
val kotlinSerializationVersion: String by project

plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
    kotlin("kapt")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core:application"))
    implementation(project(":core:domain"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}
