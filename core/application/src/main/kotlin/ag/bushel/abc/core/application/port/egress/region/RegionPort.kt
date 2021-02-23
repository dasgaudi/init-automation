package ag.bushel.abc.core.application.port.egress.region

import ag.bushel.abc.core.application.port.egress.generic.FinderPort
import ag.bushel.abc.core.application.port.egress.generic.HardDeletePort
import ag.bushel.abc.core.application.port.egress.generic.SavePort
import ag.bushel.abc.core.domain.model.Region

interface RegionPort :
    FinderPort<Region>,
    SavePort<Region>,
    HardDeletePort
