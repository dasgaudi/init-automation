package ag.bushel.abc.infrastructure.config

import ag.bushel.abc.core.application.port.egress.customer.CustomerPort
import ag.bushel.abc.core.application.port.egress.facility.FacilityPort
import ag.bushel.abc.core.application.port.egress.region.RegionPort
import ag.bushel.abc.infrastructure.config.factory.DataFactory
import ag.bushel.abc.infrastructure.config.factory.DataFactoryBuilder
import ag.bushel.abc.infrastructure.config.factory.setup.setupCustomer
import ag.bushel.abc.infrastructure.config.factory.setup.setupFacility
import ag.bushel.abc.infrastructure.config.factory.setup.setupRegion
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataFactoryConfig(
    private val customerPort: CustomerPort,
    private val facilityPort: FacilityPort,
    private val regionPort: RegionPort
) {
    @Bean
    fun dataFactory(): DataFactory = DataFactoryBuilder().run {
        setupCustomer(this, customerPort)
        setupFacility(this, facilityPort)
        setupRegion(this, regionPort)

        build()
    }
}
