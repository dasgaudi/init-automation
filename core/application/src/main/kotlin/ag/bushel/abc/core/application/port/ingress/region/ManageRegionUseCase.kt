package ag.bushel.abc.core.application.port.ingress.region

import ag.bushel.abc.core.domain.model.Region
import java.util.*

interface ManageRegionUseCase {
    fun create(region: Region): Region
    fun update(id: UUID, command: UpdateRegionCommand): Region
    fun delete(id: UUID)

    class UpdateRegionCommand(
        val name: String
    )
}
