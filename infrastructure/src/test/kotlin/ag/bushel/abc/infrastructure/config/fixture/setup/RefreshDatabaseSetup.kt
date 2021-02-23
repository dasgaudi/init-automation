package ag.bushel.abc.infrastructure.config.fixture.setup

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class RefreshDatabaseSetup {
    @Autowired
    lateinit var dataSource: DataSource

    @BeforeEach
    fun cleanDatabase() {
        val populator = ResourceDatabasePopulator()
        populator.addScripts(
            ClassPathResource("truncateTables.sql")
        )
        populator.setSeparator("@@")
        populator.execute(dataSource)
    }
}
