package ag.bushel.abc.adapter.persistence.entity

import ag.bushel.abc.core.domain.model.Facility
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "facility")
class FacilityEntity(
    override val id: UUID,
    val regionId: UUID,
    val name: String
) : EntityBase(id)

fun FacilityEntity.toModel() = Facility(
    id = id,
    regionId = regionId,
    name = name,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Facility.toEntity() = FacilityEntity(
    id = id,
    regionId = regionId,
    name = name
)
