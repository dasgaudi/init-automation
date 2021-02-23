package ag.bushel.abc.adapter.persistence.repository

import ag.bushel.abc.adapter.persistence.dao.FacilityDao
import ag.bushel.abc.adapter.persistence.entity.FacilityEntity
import ag.bushel.abc.adapter.persistence.entity.toEntity
import ag.bushel.abc.adapter.persistence.entity.toModel
import ag.bushel.abc.core.application.port.egress.facility.FacilityPort
import ag.bushel.abc.core.domain.model.Facility
import org.springframework.stereotype.Repository

@Repository
open class FacilityRepository(
    override val dao: FacilityDao
) : FacilityPort, BaseRepository<Facility, FacilityEntity>() {
    override val convertToEntity = { model: Facility -> model.toEntity() }
    override val convertToModel = { entity: FacilityEntity -> entity.toModel() }
}
