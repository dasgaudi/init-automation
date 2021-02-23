description = "infrastructure"

val springJdbcVersion: String by project
val restAssuredVersion: String by project
val liquibaseCoreVersion: String by project
val mariaDbConnectorJavaVersion: String by project
val assertKVersion: String by project
val kotlinFakerVersion: String by project
val cucumberJavaVersion: String by project
val archUnitVersion: String by project

plugins {
    kotlin("plugin.spring")

    id("org.springframework.boot")
    id("org.liquibase.gradle")
    id("karibou.java-conventions")
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:application"))
    implementation(project(":adapter:web"))
    implementation(project(":adapter:persistence"))
    implementation(project(":adapter:gateway"))

    implementation("org.springframework.boot:spring-boot-starter")

    liquibaseRuntime("ch.qos.logback:logback-classic:1.2.3")
    liquibaseRuntime("ch.qos.logback:logback-core:1.2.3")
    liquibaseRuntime("org.mariadb.jdbc:mariadb-java-client:$mariaDbConnectorJavaVersion")
    liquibaseRuntime("org.liquibase:liquibase-core:$liquibaseCoreVersion")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:$mariaDbConnectorJavaVersion")
    implementation("org.liquibase:liquibase-core:$liquibaseCoreVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.rest-assured:rest-assured-common:$restAssuredVersion")
    testImplementation("io.rest-assured:json-path:$restAssuredVersion")
    testImplementation("io.rest-assured:xml-path:$restAssuredVersion")
    testImplementation("io.github.serpro69:kotlin-faker:$kotlinFakerVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-jdbc:$springJdbcVersion")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertKVersion")
    testImplementation("com.tngtech.archunit:archunit-junit5:$archUnitVersion")

    testImplementation("io.cucumber:cucumber-java:$cucumberJavaVersion")
    testImplementation("io.cucumber:cucumber-core:$cucumberJavaVersion")
    testImplementation("io.cucumber:cucumber-junit:$cucumberJavaVersion")
    testImplementation("io.cucumber:cucumber-spring:$cucumberJavaVersion")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.7.0")
}

liquibase {
    val config = (rootProject.extra["config"] as groovy.util.ConfigObject).flatten()

    activities.register("main") {
        this.arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to config["infrastructure.spring.datasource.changelog"],
            "url" to config["infrastructure.spring.datasource.url"],
            "username" to config["infrastructure.spring.datasource.username"],
            "password" to config["infrastructure.spring.datasource.password"]
        )
    }
}

tasks.create<Copy>("unpackJar") {
    val jarPath = file("${buildDir}/libs/infrastructure-1.0.0.jar")
    val outputDir = file("${buildDir}/unpacked/")

    from(zipTree(jarPath))
    into(outputDir)
}
