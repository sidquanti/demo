package com.example.demo.exception

import org.springframework.http.HttpStatus

interface ErrorType {

    fun status(): HttpStatus

    fun type(): String
}
