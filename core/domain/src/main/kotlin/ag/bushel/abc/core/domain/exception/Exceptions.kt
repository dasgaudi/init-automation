package ag.bushel.abc.core.domain.exception

import java.time.Instant
import java.util.*
import kotlin.reflect.KClass

sealed class DomainException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

class BadGatewayException(message: String = "Bad Gateway") : DomainException(message) {
    constructor(
        requestUrl: String,
        error: String
    ) : this("External API request received an invalid response indicating a server error [url:$requestUrl, error:$error]")
}

class BadRequestException(message: String = "Bad Request") : DomainException(message)
class ForbiddenException(message: String = "Forbidden") : DomainException(message)

class GatewayUnhandled4xxException(message: String = "Unexpected Exception Occurred") : DomainException(message) {
    constructor(
        requestUrl: String,
        timestamp: Instant = Instant.now()
    ) : this("External api request to $requestUrl failed at $timestamp | Unhandled 4xx Error")
}

class NotFoundException(message: String = "Not Found") : DomainException(message) {
    constructor(
        modelType: KClass<*>,
        id: UUID? = null
    ) : this("${modelType.simpleName} not found${if (id != null) " [id:$id]" else ""}")
}
