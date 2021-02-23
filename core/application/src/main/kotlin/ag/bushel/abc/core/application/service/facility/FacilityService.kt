package ag.bushel.abc.core.application.service.facility

import ag.bushel.abc.core.application.port.egress.facility.FacilityPort
import ag.bushel.abc.core.application.port.ingress.facility.FindFacilityUseCase
import ag.bushel.abc.core.application.port.ingress.facility.ManageFacilityUseCase
import ag.bushel.abc.core.application.port.ingress.generic.findOrFail
import ag.bushel.abc.core.domain.model.Facility
import org.springframework.stereotype.Service
import java.util.*

@Service
class FacilityService(
    private val facilityPort: FacilityPort
) : FindFacilityUseCase, ManageFacilityUseCase {
    override fun findAll(): List<Facility> {
        return facilityPort.findAll()
    }

    override fun find(id: UUID): Facility? {
        return facilityPort.findById(id)
    }

    override fun create(facility: Facility): Facility {
        return facilityPort.save(facility)
    }

    override fun update(id: UUID, command: ManageFacilityUseCase.UpdateFacilityCommand): Facility {
        val facility = this.findOrFail(id)

        val updated = facility.copy(
            name = command.name
        )

        return facilityPort.save(updated)
    }

    override fun delete(id: UUID) {
        val facility = this.findOrFail(id)

        facilityPort.delete(facility.id)
    }
}
