package ag.bushel.abc.adapter.persistence.entity

import ag.bushel.abc.core.domain.model.Customer
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "customer")
class CustomerEntity(
    override val id: UUID,
    val name: String,
    val address: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null
) : EntityBase(id)

fun CustomerEntity.toModel() = Customer(
    id = id,
    name = name,
    address = address,
    city = city,
    state = state,
    postalCode = postalCode,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Customer.toEntity() = CustomerEntity(
    id = id,
    name = name,
    address = address,
    city = city,
    state = state,
    postalCode = postalCode
)
