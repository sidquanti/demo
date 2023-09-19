package com.example.demo.auth.model

data class VerifiedToken(
    val app: String,
    val userId: String,
    val name: String,
    val email: String,
    val tenant: String,
    val store: String,
    val client: Long
)
