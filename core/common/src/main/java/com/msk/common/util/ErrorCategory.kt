package com.msk.common.util

enum class ErrorCategory(val messageKey: String) {
    UnknownError("error_unknown"),
    ServerError("error_system"),
    NetworkUnavailable("error_check_internet"),
    NotFound("error_not_found"),
    Timeout("error_response_not_received"),
    Unauthorized("error_unauthorized"),
    BadRequest("error_bad_request"),
    Forbidden("error_forbidden"),
    TooManyRequests("error_too_many_requests");

    companion object {
        fun fromHttpCode(code: Int): ErrorCategory {
            return when (HttpStatusCode.fromCode(code)) {
                HttpStatusCode.ServerError -> ServerError
                HttpStatusCode.NotFound -> NotFound
                HttpStatusCode.Unauthorized -> Unauthorized
                HttpStatusCode.BadRequest -> BadRequest
                HttpStatusCode.Forbidden -> Forbidden
                HttpStatusCode.TooManyRequests -> TooManyRequests
                else -> UnknownError
            }
        }

        fun fromThrowable(error: Throwable): ErrorCategory {
            return when (error) {
                is java.net.UnknownHostException,
                is java.net.ConnectException -> NetworkUnavailable
                is java.net.SocketTimeoutException -> Timeout
                else -> UnknownError
            }
        }
    }
}

private enum class HttpStatusCode(val codeRange: IntRange) {
    BadRequest(400..400),
    Unauthorized(401..401),
    Forbidden(403..403),
    NotFound(404..404),
    TooManyRequests(429..429),
    ServerError(500..599),
    Unknown(Int.MIN_VALUE..Int.MAX_VALUE);

    companion object {
        fun fromCode(code: Int): HttpStatusCode {
            return entries.firstOrNull { code in it.codeRange } ?: Unknown
        }
    }
}