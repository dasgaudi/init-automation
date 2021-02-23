package ag.bushel.abc.core.application.port.egress.facility

import ag.bushel.abc.core.application.port.egress.generic.FinderPort
import ag.bushel.abc.core.application.port.egress.generic.HardDeletePort
import ag.bushel.abc.core.application.port.egress.generic.SavePort
import ag.bushel.abc.core.domain.model.Facility

interface FacilityPort :
    FinderPort<Facility>,
    SavePort<Facility>,
    HardDeletePort
