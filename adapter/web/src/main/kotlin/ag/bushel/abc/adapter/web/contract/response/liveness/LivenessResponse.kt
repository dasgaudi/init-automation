package ag.bushel.abc.adapter.web.contract.response.liveness

enum class ResponseStatus(var message: String) {
    SUCCESS("success"),
    ERROR("error")
}

data class LivenessResponse(
    val status: ResponseStatus = ResponseStatus.SUCCESS,
    val message: String = ""
)

data class ReadinessResponse(
    val status: ResponseStatus = ResponseStatus.SUCCESS,
    val message: String = ""
)
