package com.example.demo.auth.model

import org.springframework.security.authentication.AbstractAuthenticationToken

class AuthenticationToken(val user: String, val tenant: String?, val userName: String?, val app: String?) : AbstractAuthenticationToken(null) {

  override fun getCredentials(): Any? {
    return ""
  }

  override fun getPrincipal(): Any {
    return user
  }

  override fun isAuthenticated() = true

  companion object {
    private val serialVersionUID = 1L
  }
}
