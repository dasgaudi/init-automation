package ag.bushel.abc.adapter.web.controller

import ag.bushel.abc.adapter.web.config.annotation.ReadinessResponseOperation
import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.response.liveness.ReadinessResponse
import ag.bushel.abc.core.application.port.ingress.customer.FindCustomerUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadinessController(private val findCustomerUseCase: FindCustomerUseCase) : ControllerBase() {
    @ReadinessResponseOperation
    @GetMapping(ApiRoutes.Liveness.ready)
    fun getIsLive(): ResponseEntity<ReadinessResponse> {
        /**
         * We want to make sure that we can access the database with the various count queries as part of the readiness
         * checks so that we get the nodes to restart when db credentials change, or at least that's the hope.
         */
        // TODO: Switch to a simpler query that returns a count when JPA Specification Changes Land
        findCustomerUseCase.findAll()

        return ResponseEntity.ok(ReadinessResponse())
    }
}
