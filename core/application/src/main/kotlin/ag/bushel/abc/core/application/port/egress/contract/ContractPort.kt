package ag.bushel.abc.core.application.port.egress.contract

import ag.bushel.abc.core.application.port.egress.generic.FinderPort
import ag.bushel.abc.core.domain.model.Contract

interface ContractPort :
    FinderPort<Contract>
