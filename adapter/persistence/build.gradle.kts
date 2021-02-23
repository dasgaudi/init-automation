description = "adapter.persistence"

val mariaDbConnectorJavaVersion: String by project
val assertKVersion: String by project

plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
    id("karibou.java-conventions")
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:application"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mariadb.jdbc:mariadb-java-client:$mariaDbConnectorJavaVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertKVersion")
}
