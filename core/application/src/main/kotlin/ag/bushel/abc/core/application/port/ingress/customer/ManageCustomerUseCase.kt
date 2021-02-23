package ag.bushel.abc.core.application.port.ingress.customer

import ag.bushel.abc.core.domain.model.Customer
import java.util.*

interface ManageCustomerUseCase {
    fun create(customer: Customer): Customer
    fun update(id: UUID, command: UpdateCustomerCommand): Customer
    fun delete(id: UUID)

    class UpdateCustomerCommand(
        val name: String,
        val address: String? = null,
        val city: String? = null,
        val state: String? = null,
        val postalCode: String? = null
    )
}
