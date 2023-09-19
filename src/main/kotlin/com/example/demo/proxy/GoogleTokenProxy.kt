/*
package com.example.demo.proxy

import com.example.demo.model.GoogleTokenResponse
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "google-token-service", url = "https://www.googleapis.com/")
interface GoogleTokenProxy {

  @GetMapping("/oauth2/v3/tokeninfo")
  fun verify(@RequestParam("id_token") token: String): GoogleTokenResponse?

  @Component
  class GoogleTokenProxyFallback : GoogleTokenProxy {
    companion object {
      private val log = LoggerFactory.getLogger(GoogleTokenProxyFallback::class.java)
    }

    override fun verify(token: String) : GoogleTokenResponse? {
      log.info("verify returned null")
      return null
    }
  }
}
*/
