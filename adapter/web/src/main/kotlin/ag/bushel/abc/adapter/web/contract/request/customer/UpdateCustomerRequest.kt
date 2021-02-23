package ag.bushel.abc.adapter.web.contract.request.customer

data class UpdateCustomerRequest(
    val name: String,
    val address: String?,
    val city: String?,
    val state: String?,
    val postalCode: String?
)
