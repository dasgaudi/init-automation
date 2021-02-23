package ag.bushel.abc.core.application.port.ingress.facility

import ag.bushel.abc.core.domain.model.Facility
import java.util.*

interface ManageFacilityUseCase {
    fun create(facility: Facility): Facility
    fun update(id: UUID, command: UpdateFacilityCommand): Facility
    fun delete(id: UUID)

    class UpdateFacilityCommand(
        val name: String
    )
}
