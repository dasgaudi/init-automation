package ag.bushel.abc.adapter.persistence.repository

import ag.bushel.abc.adapter.persistence.dao.RegionDao
import ag.bushel.abc.adapter.persistence.entity.RegionEntity
import ag.bushel.abc.adapter.persistence.entity.toEntity
import ag.bushel.abc.adapter.persistence.entity.toModel
import ag.bushel.abc.core.application.port.egress.region.RegionPort
import ag.bushel.abc.core.domain.model.Region
import org.springframework.stereotype.Repository

@Repository
open class RegionRepository(
    override val dao: RegionDao
) : RegionPort, BaseRepository<Region, RegionEntity>() {
    override val convertToEntity = { model: Region -> model.toEntity() }
    override val convertToModel = { entity: RegionEntity -> entity.toModel() }
}
