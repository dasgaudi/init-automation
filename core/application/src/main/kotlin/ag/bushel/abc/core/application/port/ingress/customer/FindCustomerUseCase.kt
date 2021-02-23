package ag.bushel.abc.core.application.port.ingress.customer

import ag.bushel.abc.core.application.port.ingress.generic.FinderUseCase
import ag.bushel.abc.core.domain.model.Customer

interface FindCustomerUseCase : FinderUseCase<Customer>
