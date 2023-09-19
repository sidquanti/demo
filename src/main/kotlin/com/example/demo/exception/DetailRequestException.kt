package com.example.demo.exception

class DetailRequestException : RuntimeException {

    var errorType: ErrorType
    var params: Array<Any>

    constructor(errorType: ErrorType, params: Array<Any>) : super(errorType.type(), null, false, false) {
        this.errorType = errorType
        this.params = params
    }

    constructor(errorType: ErrorType, params: Array<Any>, cause: Throwable) : super(errorType.type(), cause, false, false) {
        this.errorType = errorType
        this.params = params
    }
}
