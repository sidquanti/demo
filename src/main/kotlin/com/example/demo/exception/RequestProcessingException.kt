package com.example.demo.exception

import org.springframework.http.HttpStatus

open class RequestProcessingException(status: HttpStatus, message: String) : RuntimeException(message) {
    var status = status
        private set
}
