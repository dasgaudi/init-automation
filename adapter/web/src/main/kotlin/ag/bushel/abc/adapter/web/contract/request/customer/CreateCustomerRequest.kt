package ag.bushel.abc.adapter.web.contract.request.customer

data class CreateCustomerRequest(
    val name: String,
    val address: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null
)
