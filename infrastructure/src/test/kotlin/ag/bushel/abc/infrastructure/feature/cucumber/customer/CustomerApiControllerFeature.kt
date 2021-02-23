package ag.bushel.abc.infrastructure.feature.cucumber.customer

import ag.bushel.abc.adapter.web.contract.ApiRoutes
import ag.bushel.abc.adapter.web.contract.response.customer.CustomerListResponse
import ag.bushel.abc.core.domain.model.Customer
import ag.bushel.abc.infrastructure.config.fixture.CucumberTestFixture
import ag.bushel.abc.infrastructure.config.fixture.builder.assert
import ag.bushel.abc.infrastructure.config.fixture.builder.extract
import ag.bushel.abc.infrastructure.config.fixture.builder.get
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.http.ResponseEntity

@CucumberContextConfiguration
class CustomerApiControllerFeature : CucumberTestFixture() {
    private lateinit var loadedCustomers: ResponseEntity<CustomerListResponse>

    @Before
    fun init() = this.setupCucumber()

    @Given("^I have (.+) customers in the system$")
    fun the_client_sets_up_customers(customers: Int) {
        dataFactory.create<Customer>(customers)
    }

    @When("^I get all customers$")
    fun the_client_gets_all_customers() {
        loadedCustomers = request.get(ApiRoutes.Customers.listCustomers).extract()
    }

    @Then("^it should return (.+) customers$")
    fun the_client_receives_customers(expectedCustomers: Int) {
        loadedCustomers.assert { assertThat(customers.count()).isEqualTo(expectedCustomers) }
    }
}
