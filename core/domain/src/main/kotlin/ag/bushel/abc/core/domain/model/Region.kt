package ag.bushel.abc.core.domain.model

import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import java.time.Instant
import java.util.*

data class Region(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
) {
    val facilities: List<Facility> = ArrayList()

    init {
        validate(this) {
            validate(Region::name).isNotBlank()
        }
    }
}
