package ag.bushel.abc.adapter.persistence.dao

import ag.bushel.abc.adapter.persistence.entity.RegionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RegionDao : CrudRepository<RegionEntity, UUID> {
    override fun findAll(): List<RegionEntity>
}
