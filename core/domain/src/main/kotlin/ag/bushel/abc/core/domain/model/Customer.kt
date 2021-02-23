package ag.bushel.abc.core.domain.model

import ag.bushel.abc.core.domain.extension.validateFullAddress
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import java.time.Instant
import java.util.*

data class Customer(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val address: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
) {
    init {
        validate(this) {
            validate(Customer::name).isNotBlank().hasSize(max = 50)

            validateFullAddress(Customer::address, Customer::city, Customer::state, Customer::postalCode)
        }
    }
}
