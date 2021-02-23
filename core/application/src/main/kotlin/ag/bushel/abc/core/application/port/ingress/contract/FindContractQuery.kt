package ag.bushel.abc.core.application.port.ingress.contract

import ag.bushel.abc.core.application.port.ingress.generic.FinderUseCase
import ag.bushel.abc.core.domain.model.Contract

interface FindContractQuery : FinderUseCase<Contract>
