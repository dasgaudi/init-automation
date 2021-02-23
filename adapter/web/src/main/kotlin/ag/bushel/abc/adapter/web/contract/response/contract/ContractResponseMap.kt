package ag.bushel.abc.adapter.web.contract.response.contract

import ag.bushel.abc.core.domain.model.Contract

private fun Contract.toContractResponse() = ContractResponse(
    id = id,
    contractOwnerId = contractOwnerId,
    totalValue = totalValue,
    settledValue = settledValue,
    silos = silos,
    crop = crop,
    cropStored = cropStored,
    cropUnits = cropUnits
)

internal fun Contract.toContractGetResponse() = ContractGetResponse(
    contract = this.toContractResponse()
)

internal fun List<Contract>.toContractListResponse() = ContractListResponse(
    contracts = this.map { it.toContractResponse() }
)
