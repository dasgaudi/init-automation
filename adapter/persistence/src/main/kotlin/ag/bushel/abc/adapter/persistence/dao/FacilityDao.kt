package ag.bushel.abc.adapter.persistence.dao

import ag.bushel.abc.adapter.persistence.entity.FacilityEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FacilityDao : CrudRepository<FacilityEntity, UUID> {
    override fun findAll(): List<FacilityEntity>
}
