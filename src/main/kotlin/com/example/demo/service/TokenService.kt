package com.example.demo.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException
import java.util.*

@Service
class TokenService(@Value("\${app.security.key}") val key: String,
                   @Value("\${app.jwt.issuer}") val issuer: String,
                   @Value("\${app.jwt.audience}") val audience: String,
                   @Value("\${app.jwt.claim.app}") val appClaim: String,
                   @Value("\${app.jwt.claim.uid}") val uidClaim: String,
                   @Value("\${app.jwt.claim.name}") val nameClaim: String,
                   @Value("\${app.jwt.claim.user}") val userClaim: String,
                   @Value("\${app.jwt.token.expiry}") val tokenExpiry: Int) {

    companion object {
        private val log = LoggerFactory.getLogger(TokenService::class.java)
    }

    @Suppress("MagicNumber")
    val dayInMillis = 24 * 60 * 60 * 1000

    fun generate(app: String, uid: Long?, name: String?, user: String): String? {

        log.debug("Creating JWT for user: $user")
        try {

            return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(expiry())
                .withAudience(audience)
                .withClaim(appClaim, app)
                .withClaim(uidClaim, uid)
                .withClaim(nameClaim, name)
                .withClaim(userClaim, user)
                .sign(Algorithm.HMAC512(key))
        } catch (e: JWTCreationException) {
            log.error("Unable to generate token. ${e.localizedMessage}")
        } catch (e: UnsupportedEncodingException) {
            log.error("Unable to generate token. ${e.localizedMessage}")
        }

        return null
    }

    private fun expiry() = Date(System.currentTimeMillis() + tokenExpiry * dayInMillis)
}
