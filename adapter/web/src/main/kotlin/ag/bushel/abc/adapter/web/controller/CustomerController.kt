package ag.bushel.abc.adapter.web.controller

import ag.bushel.abc.adapter.web.config.annotation.CustomerApiResponseOperation
import ag.bushel.abc.adapter.web.config.annotation.CustomersApiResponseOperation
import ag.bushel.abc.adapter.web.config.annotation.NoContentApiResponseOperation
import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.request.customer.CreateCustomerRequest
import ag.bushel.abc.adapter.web.contract.request.customer.UpdateCustomerRequest
import ag.bushel.abc.adapter.web.contract.response.customer.*
import ag.bushel.abc.adapter.web.contract.response.customer.CustomerGetResponse
import ag.bushel.abc.adapter.web.contract.response.customer.CustomerListResponse
import ag.bushel.abc.core.application.port.ingress.customer.FindCustomerUseCase
import ag.bushel.abc.core.application.port.ingress.customer.ManageCustomerUseCase
import ag.bushel.abc.core.application.port.ingress.generic.findOrFail
import ag.bushel.abc.core.domain.model.Customer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CustomerController(
    private val findCustomerUseCase: FindCustomerUseCase,
    private val manageCustomerUseCase: ManageCustomerUseCase
) : ControllerBase() {

    @CustomersApiResponseOperation
    @GetMapping(ApiRoutes.Customers.listCustomers)
    fun listCustomers(): ResponseEntity<CustomerListResponse> {
        val result = findCustomerUseCase.findAll()

        return ResponseEntity.ok(
            result.toCustomerListResponse()
        )
    }

    @CustomerApiResponseOperation
    @GetMapping(ApiRoutes.Customers.getCustomer)
    fun getCustomer(@PathVariable("id") id: UUID): ResponseEntity<CustomerGetResponse> {
        val result = findCustomerUseCase.findOrFail(id)

        return ResponseEntity.ok(result.toCustomerGetResponse())
    }

    @CustomerApiResponseOperation(responseCode = "201")
    @PostMapping(ApiRoutes.Customers.createCustomer)
    fun createCustomer(@RequestBody request: CreateCustomerRequest): ResponseEntity<CustomerGetResponse> {
        val customer = Customer(
            name = request.name,
            address = request.address,
            city = request.city,
            state = request.state,
            postalCode = request.postalCode
        )

        val result = manageCustomerUseCase.create(customer)

        return ResponseEntity
            .created(getCreatedUri(result.id))
            .body(result.toCustomerGetResponse())
    }

    @CustomerApiResponseOperation
    @PutMapping(ApiRoutes.Customers.updateCustomer)
    fun updateCustomer(
        @PathVariable id: UUID,
        @RequestBody request: UpdateCustomerRequest
    ): ResponseEntity<CustomerGetResponse> {
        val command = ManageCustomerUseCase.UpdateCustomerCommand(
            name = request.name,
            address = request.address,
            city = request.city,
            state = request.state,
            postalCode = request.postalCode
        )

        val result = manageCustomerUseCase.update(id, command)

        return ResponseEntity.ok(result.toCustomerGetResponse())
    }

    @NoContentApiResponseOperation
    @DeleteMapping(ApiRoutes.Customers.deleteCustomer)
    fun deleteCustomer(@PathVariable id: UUID): ResponseEntity<Unit> {
        manageCustomerUseCase.delete(id)

        return ResponseEntity.noContent().build()
    }
}
