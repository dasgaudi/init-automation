package ag.bushel.abc.infrastructure.integration.factory

import ag.bushel.abc.core.domain.model.Customer
import ag.bushel.abc.infrastructure.config.fixture.IntegrationTestFixture
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CustomerFactoryTest : IntegrationTestFixture() {
    @Nested
    inner class CustomerDataFactory {
        @Test
        fun modelFactory_makeCustomer_shouldReturnValidResult() {
            val model = dataFactory.make<Customer>() {
                mapOf("city" to "Fargo")
            }

            assertThat(model).isNotNull()
            assertThat(model.name).isNotNull()
            assertThat(model.address).isNotNull()
            assertThat(model.city).isEqualTo("Fargo")
        }
    }
}
