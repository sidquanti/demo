package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleTokenResponse(
  val email: String,
  val exp: Long = 0,
  @JsonProperty("given_name") val name: String? = null,
  val picture: String? = null
)
