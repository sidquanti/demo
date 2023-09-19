package com.example.demo.exception

import feign.FeignException
import org.hibernate.exception.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.NoSuchMessageException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class ServiceExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        private val log = LoggerFactory.getLogger(ServiceExceptionHandler::class.java)

        private const val request_failed = "Request processing failed"
        private const val server_error = "Internal Server Error"
        private const val glitch = "Unable to process the request. Retry later"
        private const val request_timeout = "Request timeout"
        private const val request_timeout_try = "Request timeout. Try again"
        private const val forbidden = "Forbidden"
        private const val not_allowed = "User not allowed for the action"
    }

    @Autowired
    private lateinit var messages: Messages

    @ExceptionHandler(value = [RuntimeException::class])
    fun handle(ex: Exception): ResponseEntity<Any> {

        if (ex.cause != null)
            return handleExceptions(ex.cause!!)

        return handleExceptions(ex)
    }

    private fun handleExceptions(throwable: Throwable): ResponseEntity<Any> {

        if (throwable is RequestProcessingException) {
            log.info("RPE: ${getRootCause(throwable)}")

            return toErrorResponse(throwable)
        }

        if(throwable is AccessDeniedException){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ErrorResponse(forbidden, not_allowed))
        }

        if (throwable is FeignException) {
            log.info("FE: ${getRootCause(throwable)}")

            return ResponseEntity(throwable.contentUTF8(), HttpStatus.valueOf(throwable.status()))
        }

        log.error("SEH", throwable)
        return error()
    }

    private fun toErrorResponse(exception: RequestProcessingException): ResponseEntity<Any> {
        return if (exception is RequestTimeoutException) toTimeoutErrorResponse(exception)
        else ResponseEntity(ErrorResponse(request_failed, exception.message!!), exception.status)
    }

    private fun toTimeoutErrorResponse(exception: RequestProcessingException): ResponseEntity<Any> {
        return ResponseEntity(ErrorResponse(request_timeout, request_timeout_try), exception.status)
    }

    @ExceptionHandler(value = [DetailRequestException::class])
    fun handleDetailed(ex: DetailRequestException, webRequest: WebRequest): ResponseEntity<Any> {

        log.error("SEHD", ex)

        val key = ex.errorType.type()
        var message: String

        try {
            message = messages.get(key, ex.params, locale = webRequest.locale)
        } catch (e: NoSuchMessageException) {
            message = ex.params[0].toString()
        }

        return ResponseEntity(ErrorResponse(key, message), ex.errorType.status())
    }

    @ExceptionHandler(value = [Exception::class, Throwable::class])
    fun handleOthers(exception: Exception): Any? {

        val rootCause = getCauseOrDefault(exception)
        return if (rootCause.contains("broken pipe")) {
            null  //socket is closed, cannot return any response
        } else {
            log.error("SEHU", exception)
            error()
        }
    }

    private fun error(): ResponseEntity<Any> = ResponseEntity(ErrorResponse(server_error, glitch), HttpStatus.INTERNAL_SERVER_ERROR)

    private fun getCauseOrDefault(exception: Exception): String {
        return getRootCause(exception) ?: glitch
    }

    private fun getRootCause(throwable: Throwable): String? {
        return if (throwable.cause != null) {
            getRootCause(throwable.cause!!)
        } else throwable.message
    }


    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity(ErrorResponse(ex.parameterName, ex.message), HttpStatus.BAD_REQUEST)
    }

    /**
     * Handle constraint violation
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(
        ex: ConstraintViolationException,
        request: WebRequest?
    ): ResponseEntity<Any?>? {
        val errors: MutableList<String> = ArrayList()
        errors.add("`${ex.constraintName}")

        val errorResponseMessage = ErrorsResponse(errors, ex.message)
        return ResponseEntity(
            errorResponseMessage,
            HttpStatus.BAD_REQUEST
        )
    }

}
