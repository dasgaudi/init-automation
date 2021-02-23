package ag.bushel.abc.adapter.persistence.dao

import ag.bushel.abc.adapter.persistence.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerDao : CrudRepository<CustomerEntity, UUID> {
    override fun findAll(): List<CustomerEntity>
}
