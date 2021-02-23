package ag.bushel.abc.adapter.persistence.repository

import ag.bushel.abc.adapter.persistence.dao.CustomerDao
import ag.bushel.abc.adapter.persistence.entity.CustomerEntity
import ag.bushel.abc.adapter.persistence.entity.toEntity
import ag.bushel.abc.adapter.persistence.entity.toModel
import ag.bushel.abc.core.application.port.egress.customer.CustomerPort
import ag.bushel.abc.core.domain.model.Customer
import org.springframework.stereotype.Repository

@Repository
open class CustomerRepository(
    override val dao: CustomerDao
) : CustomerPort, BaseRepository<Customer, CustomerEntity>() {

    override val convertToEntity = { model: Customer -> model.toEntity() }
    override val convertToModel = { entity: CustomerEntity -> entity.toModel() }
}
