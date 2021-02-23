package ag.bushel.abc.core.application.service.customer

import ag.bushel.abc.core.application.port.egress.customer.CustomerPort
import ag.bushel.abc.core.application.port.ingress.customer.FindCustomerUseCase
import ag.bushel.abc.core.application.port.ingress.customer.ManageCustomerUseCase
import ag.bushel.abc.core.application.port.ingress.generic.findOrFail
import ag.bushel.abc.core.domain.model.Customer
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    private val customerPort: CustomerPort
) : FindCustomerUseCase, ManageCustomerUseCase {
    override fun findAll(): List<Customer> {
        return customerPort.findAll()
    }

    override fun find(id: UUID): Customer? {
        return customerPort.findById(id)
    }

    override fun create(customer: Customer): Customer {
        return customerPort.save(customer)
    }

    override fun update(id: UUID, command: ManageCustomerUseCase.UpdateCustomerCommand): Customer {
        val customer = this.findOrFail(id)

        val updated = customer.copy(
            name = command.name,
            address = command.address,
            city = command.city,
            state = command.state,
            postalCode = command.postalCode
        )

        return customerPort.save(updated)
    }

    override fun delete(id: UUID) {
        val customer = this.findOrFail(id)

        return customerPort.delete(customer.id)
    }
}
