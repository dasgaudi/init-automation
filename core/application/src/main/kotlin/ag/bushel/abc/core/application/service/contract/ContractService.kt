package ag.bushel.abc.core.application.service.contract

import ag.bushel.abc.core.application.port.egress.contract.ContractPort
import ag.bushel.abc.core.application.port.ingress.contract.FindContractQuery
import ag.bushel.abc.core.domain.model.Contract
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContractService(
    private val contractGateway: ContractPort
) : FindContractQuery {
    override fun findAll(): List<Contract> {
        return contractGateway.findAll()
    }

    override fun find(id: UUID): Contract? {
        return contractGateway.findById(id)
    }
}
