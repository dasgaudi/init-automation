package ag.bushel.abc.adapter.gateway.provider
import ag.bushel.abc.adapter.gateway.dto.ContractDto
import ag.bushel.abc.adapter.gateway.dto.ContractListDto
import ag.bushel.abc.adapter.gateway.dto.toModel
import ag.bushel.abc.core.application.port.egress.contract.ContractPort
import ag.bushel.abc.core.domain.model.Contract
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class ContractProvider : ContractPort {
    @Value("\${gateway.base.api.path}")
    lateinit var apiPath: String

    private val webClientRequestBuilder: WebClientRequestBuilder get() = WebClientRequestBuilder(apiPath)

    private val jsonDecoder = Json { allowSpecialFloatingPointValues = true }

    override fun findById(id: UUID): Contract? {
        val response: String = webClientRequestBuilder.get("/contracts/$id")

        val contract: ContractDto = jsonDecoder.decodeFromString(response)

        return contract.toModel()
    }

    override fun findAll(): List<Contract> {
        val response: String = webClientRequestBuilder.get("/contracts")

        val contractList: ContractListDto = jsonDecoder.decodeFromString(response)

        return contractList.contracts.map { it.toModel() }
    }
}
