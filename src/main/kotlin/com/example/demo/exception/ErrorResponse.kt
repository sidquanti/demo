package com.example.demo.exception

data class ErrorResponse(val error: String, val message: String) {
    constructor() : this("", "")
}


data class ErrorsResponse(val errors: List<String>, val message: String?) {
    constructor() : this(listOf(), "")
}
