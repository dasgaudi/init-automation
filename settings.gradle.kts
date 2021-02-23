rootProject.name = "abc"

include(
    "infrastructure",
    "core:domain",
    "core:application",
    "adapter:persistence",
    "adapter:web",
    "adapter:gateway",
    "code-coverage-report"
)

pluginManagement {
    val kotlinVersion: String by settings
    val kotlinterVersion: String by settings
    val springDependencyVersion: String by settings
    val springBootVersion: String by settings
    val liquibaseGradleVersion: String by settings
    val kaptVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.jmailen.kotlinter" -> useVersion(kotlinterVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "org.liquibase.gradle" -> useVersion(liquibaseGradleVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kaptVersion)
                "org.jetbrains.kotlin.plugin.serialization" -> useVersion(kotlinVersion)
            }
        }
    }
}
