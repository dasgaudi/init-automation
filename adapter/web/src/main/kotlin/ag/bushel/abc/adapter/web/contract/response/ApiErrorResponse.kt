package ag.bushel.abc.adapter.web.contract.response

import java.time.Instant

data class ApiErrorResponse(
    val error: String,
    val message: String,
    val stackTrace: List<StackTraceElement>? = null,
    val timestamp: Instant? = Instant.now()
)

data class ApiValidationResponse(
    val error: String,
    val message: String,
    val validation: Map<String, String>? = null,
    val stackTrace: List<StackTraceElement>? = null,
    val timestamp: Instant? = Instant.now()
)
