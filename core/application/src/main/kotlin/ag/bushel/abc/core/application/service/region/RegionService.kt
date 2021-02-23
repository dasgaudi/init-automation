package ag.bushel.abc.core.application.service.region

import ag.bushel.abc.core.application.port.egress.region.RegionPort
import ag.bushel.abc.core.application.port.ingress.generic.findOrFail
import ag.bushel.abc.core.application.port.ingress.region.FindRegionUseCase
import ag.bushel.abc.core.application.port.ingress.region.ManageRegionUseCase
import ag.bushel.abc.core.domain.model.Region
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegionService(
    private val regionPort: RegionPort
) : FindRegionUseCase, ManageRegionUseCase {
    override fun findAll(): List<Region> {
        return regionPort.findAll()
    }

    override fun find(id: UUID): Region? {
        return regionPort.findById(id)
    }

    override fun create(region: Region): Region {
        return regionPort.save(region)
    }

    override fun update(id: UUID, command: ManageRegionUseCase.UpdateRegionCommand): Region {
        val region = this.findOrFail(id)

        val updated = region.copy(
            name = command.name
        )

        return regionPort.save(updated)
    }

    override fun delete(id: UUID) {
        val region = this.findOrFail(id)

        regionPort.delete(region.id)
    }
}
