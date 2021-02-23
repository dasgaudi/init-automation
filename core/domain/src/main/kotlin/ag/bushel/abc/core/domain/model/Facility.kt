package ag.bushel.abc.core.domain.model

import java.time.Instant
import java.util.*

data class Facility(
    val id: UUID = UUID.randomUUID(),
    val regionId: UUID,
    val name: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
) {
    var region: Region? = null
}
