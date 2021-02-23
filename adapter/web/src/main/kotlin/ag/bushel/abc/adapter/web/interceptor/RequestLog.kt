package ag.bushel.abc.adapter.web.interceptor

data class RequestLog(
    val requestURI: String,
    val method: String,
    val queryString: String? = null,
    val sessionId: String? = null
)
