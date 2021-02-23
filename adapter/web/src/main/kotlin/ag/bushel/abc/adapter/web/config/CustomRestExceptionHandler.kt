package ag.bushel.abc.adapter.web.config

import ag.bushel.abc.adapter.web.contract.response.ApiErrorResponse
import ag.bushel.abc.adapter.web.contract.response.ApiValidationResponse
import ag.bushel.abc.core.domain.exception.*
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.mapToMessage
import java.lang.reflect.UndeclaredThrowableException
import java.time.format.DateTimeParseException

@ControllerAdvice
class CustomRestExceptionHandler(
    val environment: Environment
) : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Throwable::class)
    fun handle(ex: Throwable, request: WebRequest): ResponseEntity<Any> {
        val status = when (ex) {
            is AccessDeniedException -> HttpStatus.UNAUTHORIZED
            is BadGatewayException -> HttpStatus.BAD_GATEWAY
            is BadRequestException -> HttpStatus.BAD_REQUEST
            is ConstraintViolationException -> HttpStatus.UNPROCESSABLE_ENTITY
            is DateTimeParseException -> HttpStatus.BAD_REQUEST
            is ForbiddenException -> HttpStatus.FORBIDDEN
            is GatewayUnhandled4xxException -> HttpStatus.BAD_REQUEST
            is IllegalArgumentException -> HttpStatus.BAD_REQUEST
            is InvalidFormatException -> HttpStatus.BAD_REQUEST
            is JsonParseException -> HttpStatus.BAD_REQUEST
            is MissingKotlinParameterException -> HttpStatus.BAD_REQUEST
            is NotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        ex.printStackTrace()

        val debug = environment.acceptsProfiles { activeProfiles -> activeProfiles.test("test") }

        if (ex is ConstraintViolationException) {
            return ResponseEntity(
                ApiValidationResponse(
                    error = "Validation Error",
                    message = "You have validation errors in your submission",
                    validation = ex.constraintViolations.mapToMessage().map { it.property to it.message }.toMap(),
                    stackTrace = if (debug) ex.stackTrace.toList() else null
                ),
                status
            )
        }

        return ResponseEntity(
            ApiErrorResponse(
                error = status.reasonPhrase,
                message = ex.message ?: status.reasonPhrase,
                stackTrace = if (debug) ex.stackTrace.toList() else null
            ),
            status
        )
    }

    @ExceptionHandler(UndeclaredThrowableException::class)
    fun unwrapCauseAndHandle(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        println("unwrapCauseAndHandle error handled")
        return handle(ex.cause ?: ex, request)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        println("handleHttpMessageNotReadable error handled")
        return handle(ex.cause ?: ex, request)
    }
}
