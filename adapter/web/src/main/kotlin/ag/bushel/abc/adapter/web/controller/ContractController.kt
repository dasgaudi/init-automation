package ag.bushel.abc.adapter.web.controller
import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.response.contract.ContractGetResponse
import ag.bushel.abc.adapter.web.contract.response.contract.ContractListResponse
import ag.bushel.abc.adapter.web.contract.response.contract.toContractGetResponse
import ag.bushel.abc.adapter.web.contract.response.contract.toContractListResponse
import ag.bushel.abc.core.application.port.ingress.contract.FindContractQuery
import ag.bushel.abc.core.application.port.ingress.generic.findOrFail
import ag.bushel.abc.core.domain.model.Contract
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ContractController(
    private val query: FindContractQuery
) : ControllerBase() {

    @GetMapping(ApiRoutes.Contracts.getContract)
    fun getCustomer(@PathVariable("id") id: UUID): ResponseEntity<ContractGetResponse> {
        val result: Contract = query.findOrFail(id)

        return ResponseEntity.ok(result.toContractGetResponse())
    }

    @GetMapping(ApiRoutes.Contracts.listContracts)
    fun listCustomers(): ResponseEntity<ContractListResponse> {
        val result = query.findAll()

        return ResponseEntity.ok(
            result.toContractListResponse()
        )
    }
}
