package com.example.demo.auth.service

interface TokenVerifierService {

    fun verify(token: String): Map<String, Any>
}
