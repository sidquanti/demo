package com.example.demo.exception

import org.springframework.http.HttpStatus

class RequestTimeoutException(message: String) : RequestProcessingException(HttpStatus.GATEWAY_TIMEOUT, message)
