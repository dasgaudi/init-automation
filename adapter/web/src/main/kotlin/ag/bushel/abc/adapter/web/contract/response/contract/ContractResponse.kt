package ag.bushel.abc.adapter.web.contract.response.contract

import java.util.*

data class ContractResponse(
    val id: UUID,
    val contractOwnerId: UUID,
    val totalValue: Double,
    val settledValue: Double,
    val silos: Int,
    val crop: String,
    val cropStored: Double,
    val cropUnits: String
)

data class ContractListResponse(
    val contracts: List<ContractResponse>
)

data class ContractGetResponse(
    val contract: ContractResponse
)
