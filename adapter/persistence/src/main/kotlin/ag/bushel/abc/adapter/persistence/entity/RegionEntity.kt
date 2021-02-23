package ag.bushel.abc.adapter.persistence.entity

import ag.bushel.abc.core.domain.model.Region
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "region")
class RegionEntity(
    override val id: UUID,
    val name: String
) : EntityBase(id)

fun RegionEntity.toModel() = Region(
    id = id,
    name = name,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Region.toEntity() = RegionEntity(
    id = id,
    name = name
)
