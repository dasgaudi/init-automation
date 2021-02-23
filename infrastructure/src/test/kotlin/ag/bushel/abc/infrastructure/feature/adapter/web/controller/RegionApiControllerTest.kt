package ag.bushel.abc.infrastructure.feature.adapter.web.controller

import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.request.region.CreateRegionRequest
import ag.bushel.abc.adapter.web.contract.request.region.UpdateRegionRequest
import ag.bushel.abc.adapter.web.contract.response.ApiErrorResponse
import ag.bushel.abc.adapter.web.contract.response.region.RegionGetResponse
import ag.bushel.abc.adapter.web.contract.response.region.RegionListResponse
import ag.bushel.abc.core.domain.extension.replace
import ag.bushel.abc.core.domain.model.Region
import ag.bushel.abc.infrastructure.config.fixture.FeatureTestFixture
import ag.bushel.abc.infrastructure.config.fixture.builder.*
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.util.*

class RegionApiControllerTest : FeatureTestFixture() {
    @Nested
    inner class ListRegionsApiRequest {
        @Test
        fun `when all Regions are requested then Regions list is returned`() {
            dataFactory.create<Region>(3)

            request.get(ApiRoutes.Regions.listRegions)
                .extract<RegionListResponse>()
                .assert {
                    assertThat(regions.count()).isEqualTo(3)
                }
        }
    }

    @Nested
    inner class GetRegionApiRequest {
        @Test
        fun `when Region is requested with valid Id then Region is returned`() {
            val model = dataFactory.create<Region>()

            request.get(ApiRoutes.Regions.getRegion.replace("{id}", model.id))
                .extract<RegionGetResponse>()
                .assert(200) {
                    assertThat(region).isNotNull()
                    assertThat(region.name).isEqualTo(model.name)
                }
        }

        @Test
        fun `when Region is requested with invalid Id then not found exception is thrown and 404 error returned`() {
            request.get(ApiRoutes.Regions.getRegion.replace("{id}", UUID.randomUUID()))
                .extract<ApiErrorResponse>()
                .assert(404) {
                    assertThat(this).isNotNull()
                    assertThat(error).isEqualTo("Not Found")
                }
        }
    }

    @Nested
    inner class CreateRegionApiRequest {
        @Test
        fun `when creating a Region with valid request body then return created Region and check that record exists`() {
            val body = CreateRegionRequest(name = "My New Region")

            val result = request.post(ApiRoutes.Regions.createRegion, body)
                .extract<RegionGetResponse>()
                .assert(201) {
                    assertThat(this).isNotNull()
                    assertThat(region.name).isEqualTo("My New Region")
                }.body()

            assertThat(
                dataFactory.exists<Region>(result.region.id)
            ).isTrue()
        }
    }

    @Nested
    inner class UpdateRegionApiRequest {
        @Test
        fun `when updating a Region with valid request body then return updated Region and check that record is updated`() {
            val model = dataFactory.create<Region>()

            val updateRequest = UpdateRegionRequest(name = "${model.name} (Updated)")

            request.put(ApiRoutes.Regions.updateRegion.replace("{id}", model.id), updateRequest)
                .extract<RegionGetResponse>()
                .assert(200) {
                    assertThat(this).isNotNull()
                    assertThat(region.id).isEqualTo(model.id)
                    assertThat(region.name).isEqualTo(updateRequest.name)
                }

            // check that record was updated in database
            dataFactory.find<Region>(model.id)?.let {
                assertThat(it.name).isEqualTo(updateRequest.name)
            } ?: fail("Record shouldn't be null")
        }
    }

    @Nested
    inner class DeleteRegionApiRequest {
        @Test
        fun `when deleting a Region with valid Id then return 204 and check that record doesn't exist`() {
            val model = dataFactory.create<Region>()

            request.delete(ApiRoutes.Regions.deleteRegion.replace("{id}", model.id))
                .then().statusCode(204)

            assertThat(
                dataFactory.exists<Region>(model.id)
            ).isFalse()
        }

        @Test
        fun `when deleting a Region with invalid Id then not found exception is thrown and 404 error returned`() {
            request.delete(ApiRoutes.Regions.deleteRegion.replace("{id}", UUID.randomUUID()))
                .extract<ApiErrorResponse>()
                .assert(404) {
                    assertThat(this).isNotNull()
                    assertThat(error).isEqualTo("Not Found")
                }
        }
    }
}
