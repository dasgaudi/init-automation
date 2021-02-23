package ag.bushel.abc.adapter.web.contract.response.region

import ag.bushel.abc.core.domain.model.Region
import java.util.*

private fun Region.toRegionResponse() = RegionResponse(
    id = id,
    name = name,
    createdAt = createdAt
)

internal fun Region.toRegionGetResponse() = RegionGetResponse(
    region = this.toRegionResponse()
)

internal fun List<Region>.toRegionListResponse() = RegionListResponse(
    regions = this.map { it.toRegionResponse() }
)
