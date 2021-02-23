description = "core.application"

plugins {
    id("karibou.java-conventions")
}

dependencies {
    implementation(project(":core:domain"))

    implementation("org.springframework:spring-context")
}
