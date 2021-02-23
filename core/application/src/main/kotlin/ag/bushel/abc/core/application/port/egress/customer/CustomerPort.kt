package ag.bushel.abc.core.application.port.egress.customer

import ag.bushel.abc.core.application.port.egress.generic.FinderPort
import ag.bushel.abc.core.application.port.egress.generic.HardDeletePort
import ag.bushel.abc.core.application.port.egress.generic.SavePort
import ag.bushel.abc.core.domain.model.Customer

interface CustomerPort :
    FinderPort<Customer>,
    SavePort<Customer>,
    HardDeletePort
