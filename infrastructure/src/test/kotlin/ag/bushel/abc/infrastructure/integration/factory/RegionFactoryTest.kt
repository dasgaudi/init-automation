package ag.bushel.abc.infrastructure.integration.factory

import ag.bushel.abc.core.application.port.egress.region.RegionPort
import ag.bushel.abc.core.domain.model.Region
import ag.bushel.abc.infrastructure.config.fixture.IntegrationTestFixture
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.fail
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class RegionFactoryTest : IntegrationTestFixture() {
    @Autowired
    lateinit var repo: RegionPort

    @Nested
    inner class MakeRegion {
        @Test
        fun dataFactory_makeRegion_shouldReturnValidResult() {
            val result = dataFactory.make<Region>()

            assertThat(result).isNotNull()
            assertThat(result.name).isNotNull()
        }

        @Test
        fun dataFactory_makeRegionWithOverrides_shouldReturnValidResult() {
            val result = dataFactory.make<Region> {
                mapOf("name" to "New Region")
            }

            assertThat(result).isNotNull()
            assertThat(result.name).isEqualTo("New Region")
        }
    }

    @Nested
    inner class MakeRegionList {
        @Test
        fun dataFactory_makeRegionList_shouldReturnList() {
            val result = dataFactory.make<Region>(3)

            assertThat(result.map { it.name }.count()).isEqualTo(3)
        }
    }

    @Nested
    inner class CreateRegion {
        @Test
        fun dataFactory_createRegion_shouldReturnValidResult() {
            val result = dataFactory.create<Region>()

            assertThat(result).isNotNull()
            assertThat(result.name).isNotNull()

            repo.findById(result.id).also {
                assertThat(it).isNotNull()
            }
        }

        @Test
        fun dataFactory_createRegionWithOverrides_shouldReturnValidResult() {
            val model = dataFactory.create<Region> {
                mapOf("name" to "Custom Region Name")
            }

            assertThat(model).isNotNull()
            assertThat(model.name).isEqualTo("Custom Region Name")

            repo.findById(model.id)?.apply {
                assertThat(name).isEqualTo("Custom Region Name")
            } ?: fail("Should not be null")
        }

        @Test
        fun dataFactory_whenMakeAndCreateRegion_shouldOnlyCreateSingleRecord() {
            dataFactory.make<Region>(5)
            dataFactory.create<Region>()

            assertThat(repo.findAll().count()).isEqualTo(1)
        }
    }

    @Nested
    inner class CreateRegionList {
        @Test
        fun dataFactory_createRegionList_shouldReturnValidResult() {
            val result = dataFactory.create<Region>(3)

            assertThat(result.map { it.name }.distinct().count()).isEqualTo(3)
            assertThat(repo.findAll().count()).isEqualTo(3)
        }
    }
}
