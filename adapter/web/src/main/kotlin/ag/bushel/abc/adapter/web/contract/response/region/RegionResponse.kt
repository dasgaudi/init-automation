package ag.bushel.abc.adapter.web.contract.response.region

import java.time.Instant
import java.util.*

data class RegionResponse(
    val id: UUID,
    val name: String,
    val createdAt: Instant
)

data class RegionListResponse(
    val regions: List<RegionResponse>
)

data class RegionGetResponse(
    val region: RegionResponse
)
