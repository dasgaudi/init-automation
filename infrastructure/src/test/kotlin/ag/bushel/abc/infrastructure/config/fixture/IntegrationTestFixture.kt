package ag.bushel.abc.infrastructure.config.fixture

import ag.bushel.abc.infrastructure.config.factory.DataFactory
import ag.bushel.abc.infrastructure.config.fixture.setup.RefreshDatabaseSetup
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:application-test.properties"])
class IntegrationTestFixture : RefreshDatabaseSetup() {
    @Autowired
    lateinit var dataFactory: DataFactory
}
