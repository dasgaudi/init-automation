description = "core.domain"

val valiktorCoreVersion: String by project
val assertKVersion: String by project

plugins {
    id("karibou.java-conventions")
}

dependencies {
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.valiktor:valiktor-core:$valiktorCoreVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertKVersion")
}
