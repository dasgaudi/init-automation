package ag.bushel.abc.adapter.web.config.annotation

import ag.bushel.abc.adapter.web.contract.response.customer.CustomerGetResponse
import ag.bushel.abc.adapter.web.contract.response.customer.CustomerListResponse
import ag.bushel.abc.adapter.web.contract.response.liveness.LivenessResponse
import ag.bushel.abc.adapter.web.contract.response.liveness.ReadinessResponse
import ag.bushel.abc.adapter.web.contract.response.region.RegionGetResponse
import ag.bushel.abc.adapter.web.contract.response.region.RegionListResponse
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@ApiResponse
annotation class NoContentApiResponseOperation(
    val responseCode: String = "204",
    val description: String = "No Content Response",
    val content: Array<Content> = [Content(mediaType = "application/json")]
)

@Target(AnnotationTarget.FUNCTION)
@ApiResponse
annotation class RegionApiResponseOperation(
    val responseCode: String = "200",
    val description: String = "Region Response",
    val content: Array<Content> = [
        Content(
            mediaType = "application/json",
            schema = Schema(implementation = RegionGetResponse::class)
        )
    ]
)

@Target(AnnotationTarget.FUNCTION)
@ApiResponse
annotation class RegionsApiResponseOperation(
    val responseCode: String = "200",
    val description: String = "Regions Response",
    val content: Array<Content> = [
        Content(
            mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = RegionListResponse::class))
        )
    ]
)

@Target(AnnotationTarget.FUNCTION)
@ApiResponse
annotation class CustomerApiResponseOperation(
    val responseCode: String = "200",
    val description: String = "Customer Response",
    val content: Array<Content> = [
        Content(
            mediaType = "application/json",
            schema = Schema(implementation = CustomerGetResponse::class)
        )
    ]
)

@Target(AnnotationTarget.FUNCTION)
@ApiResponse
annotation class CustomersApiResponseOperation(
    val responseCode: String = "200",
    val description: String = "Customers Response",
    val content: Array<Content> = [
        Content(
            mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = CustomerListResponse::class))
        )
    ]
)

@Target(AnnotationTarget.FUNCTION)
@ApiResponse
annotation class LivenessResponseOperation(
    val responseCode: String = "200",
    val description: String = "Liveness Response",
    val content: Array<Content> = [
        Content(
            mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = LivenessResponse::class))
        )
    ]
)

@Target(AnnotationTarget.FUNCTION)
@ApiResponse
annotation class ReadinessResponseOperation(
    val responseCode: String = "200",
    val description: String = "Readiness Response",
    val content: Array<Content> = [
        Content(
            mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = ReadinessResponse::class))
        )
    ]
)
