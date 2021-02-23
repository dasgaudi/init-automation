package ag.bushel.abc.infrastructure.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource

@PropertySource("application.properties")
@ComponentScan("ag.bushel.abc.infrastructure.config.DataFactoryConfig")
class CucumberConfig
