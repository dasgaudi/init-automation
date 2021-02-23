package ag.bushel.abc.adapter.persistence

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
open class JpaAuditingConfig {
    @Bean
    open fun auditorProvider(): AuditorAware<String> {
        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()

          hardcoded "ewalter" for now
         */
        return AuditorAware { Optional.ofNullable("ewalter") }
    }
}
