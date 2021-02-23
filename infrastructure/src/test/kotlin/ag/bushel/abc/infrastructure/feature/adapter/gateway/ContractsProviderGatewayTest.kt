package ag.bushel.abc.infrastructure.feature.adapter.gateway

import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.response.contract.ContractGetResponse
import ag.bushel.abc.adapter.web.contract.response.contract.ContractListResponse
import ag.bushel.abc.core.domain.extension.replace
import ag.bushel.abc.infrastructure.config.fixture.FeatureTestFixture
import ag.bushel.abc.infrastructure.config.fixture.builder.assert
import ag.bushel.abc.infrastructure.config.fixture.builder.extract
import ag.bushel.abc.infrastructure.config.fixture.builder.get
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class ContractsProviderGatewayTest : FeatureTestFixture() {
    @Nested
    inner class ListContracts {
        @Test
        fun `when all Contracts are requested then Contracts list is returned`() {
            request.get(ApiRoutes.Contracts.listContracts)
                .extract<ContractListResponse>()
                .assert {
                    assertThat(contracts.count()).isGreaterThan(1)
                }
        }
    }

    @Nested
    inner class GetContract {
        @Test
        fun `when Contract is requested with valid Id then Contract is returned`() {
            val id: UUID = UUID.randomUUID()

            request.get(ApiRoutes.Contracts.getContract.replace("{id}", id))
                .extract<ContractGetResponse>()
                .assert(200) {
                    assertThat(contract).isNotNull()
                    assertThat(contract.id).isEqualTo(id)
                }
        }
    }
}
