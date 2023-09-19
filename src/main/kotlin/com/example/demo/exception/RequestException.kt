package com.example.demo.exception

import org.springframework.http.HttpStatus

class RequestException(message: String) : RequestProcessingException(HttpStatus.BAD_REQUEST, message)
