package ag.bushel.abc.adapter.web.contract.response.customer

import java.time.Instant
import java.util.*

data class CustomerResponse(
    val id: UUID,
    val name: String,
    val address: String?,
    val city: String?,
    val state: String?,
    val postalCode: String?,
    val createdAt: Instant
)

data class CustomerListResponse(
    val customers: List<CustomerResponse>
)

data class CustomerGetResponse(
    val customer: CustomerResponse
)
