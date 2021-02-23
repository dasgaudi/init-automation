package ag.bushel.abc.adapter.web.controller

import ag.bushel.abc.adapter.web.config.annotation.NoContentApiResponseOperation
import ag.bushel.abc.adapter.web.config.annotation.RegionApiResponseOperation
import ag.bushel.abc.adapter.web.config.annotation.RegionsApiResponseOperation
import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.request.region.CreateRegionRequest
import ag.bushel.abc.adapter.web.contract.request.region.UpdateRegionRequest
import ag.bushel.abc.adapter.web.contract.response.region.*
import ag.bushel.abc.adapter.web.contract.response.region.toRegionListResponse
import ag.bushel.abc.core.application.port.ingress.generic.findOrFail
import ag.bushel.abc.core.application.port.ingress.region.FindRegionUseCase
import ag.bushel.abc.core.application.port.ingress.region.ManageRegionUseCase
import ag.bushel.abc.core.domain.model.Region
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class RegionController(
    private val findRegionUseCase: FindRegionUseCase,
    private val manageRegionUseCase: ManageRegionUseCase
) : ControllerBase() {
    @RegionsApiResponseOperation
    @GetMapping(ApiRoutes.Regions.listRegions)
    fun listRegions(): ResponseEntity<RegionListResponse> {
        val result = findRegionUseCase.findAll()

        return ResponseEntity.ok(result.toRegionListResponse())
    }

    @RegionApiResponseOperation
    @GetMapping(ApiRoutes.Regions.getRegion)
    fun getRegion(@PathVariable id: UUID): ResponseEntity<RegionGetResponse> {
        val result = findRegionUseCase.findOrFail(id)

        return ResponseEntity.ok(result.toRegionGetResponse())
    }

    @RegionApiResponseOperation(responseCode = "201")
    @PostMapping(ApiRoutes.Regions.createRegion)
    fun createRegion(@RequestBody request: CreateRegionRequest): ResponseEntity<RegionGetResponse> {
        val region = Region(name = request.name)

        val result = manageRegionUseCase.create(region)

        return ResponseEntity
            .created(getCreatedUri(result.id))
            .body(result.toRegionGetResponse())
    }

    @RegionApiResponseOperation
    @PutMapping(ApiRoutes.Regions.updateRegion)
    fun updateRegion(
        @PathVariable id: UUID,
        @RequestBody request: UpdateRegionRequest
    ): ResponseEntity<RegionGetResponse> {
        val command = ManageRegionUseCase.UpdateRegionCommand(
            name = request.name
        )

        val result = manageRegionUseCase.update(id, command)

        return ResponseEntity.ok(result.toRegionGetResponse())
    }

    @NoContentApiResponseOperation
    @DeleteMapping(ApiRoutes.Regions.deleteRegion)
    fun deleteRegion(@PathVariable id: UUID): ResponseEntity<Unit> {
        manageRegionUseCase.delete(id)

        return ResponseEntity.noContent().build()
    }
}
