description = "adapter.web"

val springDocVersion: String by project
val valiktorCoreVersion: String by project

plugins {
    kotlin("plugin.spring")
    id("karibou.java-conventions")
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")
    implementation("org.valiktor:valiktor-core:$valiktorCoreVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}