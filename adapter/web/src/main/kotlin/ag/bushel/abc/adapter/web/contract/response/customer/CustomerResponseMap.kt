package ag.bushel.abc.adapter.web.contract.response.customer

import ag.bushel.abc.core.domain.model.Customer

private fun Customer.toCustomerResponse() = CustomerResponse(
    id = id,
    name = name,
    address = address,
    city = city,
    state = state,
    postalCode = postalCode,
    createdAt = createdAt
)

internal fun Customer.toCustomerGetResponse() = CustomerGetResponse(
    customer = this.toCustomerResponse()
)

internal fun List<Customer>.toCustomerListResponse() = CustomerListResponse(
    customers = this.map { it.toCustomerResponse() }
)
