package ag.bushel.abc.adapter.web.controller

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*

abstract class ControllerBase {
    protected fun getCreatedUri(id: UUID): URI {
        return ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(id).toUri()
    }
}
