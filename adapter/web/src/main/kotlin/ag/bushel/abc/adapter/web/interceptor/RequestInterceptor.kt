package ag.bushel.abc.adapter.web.interceptor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestInterceptor : HandlerInterceptor {
    private var logger: Logger = LoggerFactory.getLogger("RequestInterceptor")

    @Throws(Exception::class)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        exception: Exception?
    ) {
        val mapper = jacksonObjectMapper()

        val requestLog = RequestLog(
            requestURI = request.requestURI,
            queryString = request.queryString,
            sessionId = request.getSession(false)?.id,
            method = request.method
        )

        logger.debug(mapper.writeValueAsString(requestLog))
    }
}
