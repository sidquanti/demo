package com.example.demo.exception;

import org.springframework.http.HttpStatus

enum class ApplicationErrors : ErrorType {

    INVALID_INPUT(HttpStatus.BAD_REQUEST);

    private var httpStatus: HttpStatus

    constructor(httpStatus: HttpStatus) {
        this.httpStatus = httpStatus
    }

    override fun status(): HttpStatus {
        return httpStatus
    }

    override fun type(): String {
        return this.toString()
    }
}
