package ag.bushel.abc.adapter.gateway.provider

import ag.bushel.abc.core.domain.exception.*
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.*

class WebClientRequestBuilder(val apiPath: String) {
    val requestBuilder: WebClient = this.setup()

    private fun setup(): WebClient {
        return builder()
            .baseUrl(apiPath)
            .defaultHeader("accept", "application/json")
            .defaultHeader("content-type", "application/json")
            .build()
    }

    fun handle(request: RequestHeadersSpec<*>): ResponseSpec {
        return request.retrieve().onStatus(
            HttpStatus::is4xxClientError
        ) {
            when {
                it.statusCode() == HttpStatus.NOT_FOUND -> throw NotFoundException()
                it.statusCode() == HttpStatus.BAD_REQUEST -> throw BadRequestException()
                it.statusCode() == HttpStatus.FORBIDDEN -> throw ForbiddenException()
                else -> throw GatewayUnhandled4xxException()
            }
        }
            .onStatus(
                HttpStatus::is5xxServerError
            ) {
                throw BadGatewayException()
            }
    }

    fun getBasePath(): String {
        return this.apiPath
    }
}

inline fun <reified T> WebClientRequestBuilder.get(path: String): T {
    val response = requestBuilder.get().uri(path)

    return handle(response).bodyToMono(T::class.java).block()
        ?: throw BadGatewayException()
}
