package ag.bushel.abc.core.domain.model

import java.util.UUID

data class Contract(
    val id: UUID,
    val contractOwnerId: UUID,
    val totalValue: Double,
    val settledValue: Double,
    val silos: Int,
    val crop: String,
    val cropStored: Double,
    val cropUnits: String
)
