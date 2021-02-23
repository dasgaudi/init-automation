package ag.bushel.abc.infrastructure.architecture

import ag.bushel.abc.KaribouApplication
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition

@AnalyzeClasses(packagesOf = [KaribouApplication::class])
internal class ArchUnitTests {

    @ArchTest
    fun `modules within core should be free from cycles`(importedClasses: JavaClasses) {
        // core application is dependent on domain
        // core domain is not dependent on application
        val rule = SlicesRuleDefinition.slices()
            .matching("..core.(*)..")
            .should().beFreeOfCycles()

        rule.check(importedClasses)
    }

    @ArchTest
    fun `no classes that reside in the core module should access any classes that reside in the infrastructure module`(importedClasses: JavaClasses) {
        val rule = noClasses()
            .that()
            .resideInAPackage("..core..")
            .should()
            .accessClassesThat()
            .resideInAnyPackage("..infrastructure..")

        rule.check(importedClasses)
    }

    @ArchTest
    fun `modules within adapter should not depend on each other`(importedClasses: JavaClasses) {
        val rule = SlicesRuleDefinition.slices()
            .matching("..abc.adapter.(*)..")
            .should()
            .notDependOnEachOther()

        rule.check(importedClasses)
    }

    @ArchTest
    fun `modules within adapter should be free of cycles`(importedClasses: JavaClasses) {
        val rule = SlicesRuleDefinition.slices()
            .matching("..adapter.(*)..")
            .should()
            .beFreeOfCycles()

        rule.check(importedClasses)
    }

    @ArchTest
    fun `any classes that are annotated with @RestController should reside in the adapter web module`(importedClasses: JavaClasses) {
        val rule = classes()
            .that()
            .areAnnotatedWith("RestController")
            .should()
            .resideInAPackage("..adapter.web..")

        rule.check(importedClasses)
    }

    @ArchTest
    fun `layer architecture tests`(importedClasses: JavaClasses) {
        val rule = Architectures.layeredArchitecture()
            .layer("Core").definedBy("..core..")
            .layer("Adapter").definedBy("..adapter..")
            .layer("Infrastructure").definedBy("..infrastructure..")
            .whereLayer("Adapter").mayOnlyBeAccessedByLayers("Infrastructure")
            .whereLayer("Core").mayOnlyBeAccessedByLayers("Adapter", "Infrastructure")
            .whereLayer("Infrastructure").mayNotBeAccessedByAnyLayer()

        rule.check(importedClasses)
    }
}
