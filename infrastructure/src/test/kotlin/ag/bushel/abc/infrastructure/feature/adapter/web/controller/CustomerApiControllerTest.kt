package ag.bushel.abc.infrastructure.feature.adapter.web.controller

import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.request.customer.CreateCustomerRequest
import ag.bushel.abc.adapter.web.contract.request.customer.UpdateCustomerRequest
import ag.bushel.abc.adapter.web.contract.response.ApiErrorResponse
import ag.bushel.abc.adapter.web.contract.response.customer.CustomerGetResponse
import ag.bushel.abc.adapter.web.contract.response.customer.CustomerListResponse
import ag.bushel.abc.core.domain.extension.replace
import ag.bushel.abc.core.domain.model.Customer
import ag.bushel.abc.infrastructure.config.fixture.FeatureTestFixture
import ag.bushel.abc.infrastructure.config.fixture.builder.*
import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.util.*

class CustomerApiControllerTest : FeatureTestFixture() {
    @Nested
    inner class ListCustomers {
        @Test
        fun listCustomers_shouldReturnAllCustomers() {
            dataFactory.create<Customer>(2)

            request.get(ApiRoutes.Customers.listCustomers)
                .extract<CustomerListResponse>()
                .assert {
                    assertThat(customers.count()).isGreaterThan(1)
                }
        }
    }

    @Nested
    inner class GetCustomer {
        @Test
        fun getCustomer_whenValidId_shouldReturnCustomer() {
            val model = dataFactory.create<Customer>()

            request.get(ApiRoutes.Customers.getCustomer.replace("{id}", model.id))
                .extract<CustomerGetResponse>()
                .assert(200) {
                    assertThat(customer).isNotNull()
                    assertThat(customer.name).isEqualTo(model.name)
                }
        }

        @Test
        fun getCustomer_whenNotValidId_shouldThrowNotFoundException() {
            request.get(ApiRoutes.Customers.getCustomer.replace("{id}", UUID.randomUUID()))
                .extract<ApiErrorResponse>()
                .assert(404) {
                    assertThat(this).isNotNull()
                    assertThat(error).isEqualTo("Not Found")
                }
        }
    }

    @Nested
    inner class CreateCustomer {
        @Test
        fun createCustomer_whenValidRequest_shouldReturnCreatedCustomer() {
            val body = CreateCustomerRequest(
                name = "John Doe",
                address = "1999 test lane",
                city = "fargo",
                state = "nd",
                postalCode = "58103"
            )

            val result = request.post(ApiRoutes.Customers.createCustomer, body)
                .extract<CustomerGetResponse>()
                .assert(201) {
                    assertThat(this).isNotNull()
                    assertThat(customer.name).isEqualTo("John Doe")
                    assertThat(customer.address).isEqualTo("1999 test lane")
                    assertThat(customer.city).isEqualTo("fargo")
                    assertThat(customer.state).isEqualTo("nd")
                    assertThat(customer.postalCode).isEqualTo("58103")
                }.body()

            assertThat(
                dataFactory.exists<Customer>(result.customer.id)
            ).isTrue()
        }
    }

    @Nested
    inner class UpdateCustomer {
        @Test
        fun updateCustomer_whenValidRequest_shouldReturnUpdatedCustomer() {
            val model = dataFactory.create<Customer>()

            val updateRequest = UpdateCustomerRequest(
                name = "${model.name} (Updated)",
                address = "${model.address} (Updated)",
                city = "${model.city} (Updated)",
                state = "ND",
                postalCode = "${model.postalCode} (Updated)"
            )

            request.put(ApiRoutes.Customers.updateCustomer.replace("{id}", model.id), updateRequest)
                .extract<CustomerGetResponse>()
                .assert(200) {
                    assertThat(this).isNotNull()
                    assertThat(customer.id).isEqualTo(model.id)
                    assertThat(customer.name).isEqualTo(updateRequest.name)
                    assertThat(customer.address).isEqualTo(updateRequest.address)
                    assertThat(customer.city).isEqualTo(updateRequest.city)
                    assertThat(customer.state).isEqualTo(updateRequest.state)
                    assertThat(customer.postalCode).isEqualTo(updateRequest.postalCode)
                }

            // check that record was updated in database
            dataFactory.find<Customer>(model.id)?.let {
                assertThat(it.name).isEqualTo(updateRequest.name)
                assertThat(it.address).isEqualTo(updateRequest.address)
                assertThat(it.city).isEqualTo(updateRequest.city)
                assertThat(it.state).isEqualTo(updateRequest.state)
                assertThat(it.postalCode).isEqualTo(updateRequest.postalCode)
            } ?: fail("Record shouldn't be null")
        }
    }

    @Nested
    inner class DeleteCustomer {
        @Test
        fun deleteCustomer_whenValidId_shouldReturn204() {
            val model = dataFactory.create<Customer>()

            request.delete(ApiRoutes.Customers.deleteCustomer.replace("{id}", model.id))
                .then().statusCode(204)

            assertThat(
                dataFactory.exists<Customer>(model.id)
            ).isFalse()
        }

        @Test
        fun deleteCustomer_whenNotValidId_shouldThrowNotFoundException() {
            request.delete(ApiRoutes.Customers.deleteCustomer.replace("{id}", UUID.randomUUID()))
                .extract<ApiErrorResponse>()
                .assert(404) {
                    assertThat(this).isNotNull()
                    assertThat(error).isEqualTo("Not Found")
                }
        }
    }
}
