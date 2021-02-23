package ag.bushel.abc.infrastructure.config.fixture.setup

import ag.bushel.abc.infrastructure.config.fixture.builder.RestAssuredRequestBuilder
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.server.LocalServerPort

class RestAssuredSetup : RefreshDatabaseSetup() {
    @LocalServerPort
    var port = 0

    @BeforeAll
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
    }

    @Value("\${server.servlet.context-path}")
    lateinit var apiPath: String

    val request: RestAssuredRequestBuilder get() = RestAssuredRequestBuilder(apiPath)
}
