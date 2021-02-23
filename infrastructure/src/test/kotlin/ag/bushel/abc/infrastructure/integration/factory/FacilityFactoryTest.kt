package ag.bushel.abc.infrastructure.integration.factory

import ag.bushel.abc.core.application.port.egress.facility.FacilityPort
import ag.bushel.abc.core.application.port.egress.region.RegionPort
import ag.bushel.abc.core.domain.model.Facility
import ag.bushel.abc.core.domain.model.Region
import ag.bushel.abc.infrastructure.config.fixture.IntegrationTestFixture
import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import assertk.fail
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import java.util.*

class FacilityFactoryTest @Autowired constructor(
    private val regionRepo: RegionPort,
    private val facilityRepo: FacilityPort
) : IntegrationTestFixture() {
    @Nested
    inner class MakeFacility {
        @Test
        fun dataFactory_makeFacility_shouldReturnValidResult() {
            val result = dataFactory.make<Facility>()

            assertThat(result).isNotNull()
            assertThat(result.name).isNotNull()
        }

        @Test
        fun dataFactory_makeFacilityWithOverrides_shouldReturnValidResult() {
            val regionId = UUID.randomUUID()

            val result = dataFactory.make<Facility> {
                mapOf(
                    "regionId" to regionId,
                    "name" to "Custom Facility Name"
                )
            }

            assertThat(result).isNotNull()
            assertThat(result.regionId).isEqualTo(regionId)
            assertThat(result.name).isEqualTo("Custom Facility Name")
        }
    }

    @Nested
    inner class MakeFacilityList {
        @Test
        fun dataFactory_makeFacilityList_shouldReturnList() {
            val result = dataFactory.make<Facility>(5)

            assertThat(result.map { it.name }.count()).isEqualTo(5)
            assertThat(result.map { it.regionId }.distinct().count()).isEqualTo(5)
        }

        @Test
        fun dataFactory_makeFacilityListWithOverrides_shouldReturnListWithSameRegionId() {
            val regionId = UUID.randomUUID()

            val result = dataFactory.make<Facility>(3) {
                mapOf("regionId" to regionId)
            }

            assertThat(result.map { it.name }.count()).isEqualTo(3)
            assertThat(result.map { it.regionId }.distinct().count()).isEqualTo(1)
        }
    }

    @Nested
    inner class CreateFacility {
        @Test
        fun dataFactory_createFacility_shouldReturnValidResult() {
            val model = dataFactory.create<Facility>()

            assertThat(model).isNotNull()
            assertThat(model.regionId).isNotNull()
            assertThat(model.name).isNotNull()

            // find Facility and check values
            facilityRepo.findById(model.id)?.apply {
                assertThat(regionId).isEqualTo(model.regionId)
                assertThat(name).isEqualTo(model.name)
            } ?: fail("Should not be null")

            // Region exists and only Region record persisted
            assertThat(dataFactory.exists<Region>(model.regionId)).isTrue()
        }

        @Test
        fun dataFactory_createFacility_shouldThrowConstraintError() {
            assertThat {
                dataFactory.create<Facility> {
                    mapOf("regionId" to UUID.randomUUID())
                }
            }.isFailure().all {
                isInstanceOf(DataIntegrityViolationException::class)
            }
        }

        @Test
        fun dataFactory_createFacilityWithOverrides_shouldReturnValidResult() {
            val region = dataFactory.create<Region>()
            val facility = dataFactory.create<Facility> {
                mapOf(
                    "regionId" to region.id,
                    "name" to "Custom Facility Name"
                )
            }

            // find Facility and check values
            facilityRepo.findById(facility.id)?.apply {
                assertThat(regionId).isEqualTo(facility.regionId)
                assertThat(name).isEqualTo(facility.name)
            } ?: fail("Should not be null")

            // Region exists and is only Region record persisted
            assertThat(dataFactory.exists<Region>(facility.regionId)).isTrue()
            assertThat(regionRepo.findAll().count()).isEqualTo(1)
        }
    }

    @Nested
    inner class CreateFacilityList {
        @Test
        fun dataFactory_createFacilityList_shouldReturnList() {
            val result = dataFactory.create<Facility>(3)

            assertThat(result.map { it.name }.count()).isEqualTo(3)
            assertThat(result.map { it.regionId }.count()).isEqualTo(3)
            assertThat(facilityRepo.findAll().count()).isEqualTo(3)
            assertThat(regionRepo.findAll().count()).isEqualTo(3)
        }

        @Test
        fun dataFactory_createFacilityListWithOverrides_shouldReturnListWithSameRegionId() {
            val region = dataFactory.create<Region>()

            val result = dataFactory.create<Facility>(3) {
                mapOf("regionId" to region.id)
            }

            assertThat(result.map { it.name }.count()).isEqualTo(3)
            assertThat(result.map { it.regionId }.distinct().count()).isEqualTo(1)
            assertThat(facilityRepo.findAll().count()).isEqualTo(3)

            // only one Region record persisted and matches created above
            regionRepo.findAll().apply {
                assertThat(count()).isEqualTo(1)
                assertThat(single().id).isEqualTo(region.id)
            }
        }
    }
}
