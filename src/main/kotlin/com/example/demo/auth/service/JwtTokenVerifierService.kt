package com.example.demo.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.example.demo.auth.model.ClaimMapping
import com.example.demo.auth.model.ClaimProperties
import com.example.demo.auth.util.TokenUtil
import com.example.demo.auth.util.TokenUtil.Companion.mappingOf
import com.example.demo.auth.util.TokenUtil.Companion.parseClaims
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException

@Service
class JwtTokenVerifierService(
    @Value("\${app.security.key:sv@2023}") val key: String,
    @Value("\${app.jwt.issuer:studyvenue.in}") val issuer: String,
    @Value("\${app.jwt.audience:sv}") val audience: String,
    val claimProperties: ClaimProperties
) : TokenVerifierService {

    companion object {
        private val log = LoggerFactory.getLogger(JwtTokenVerifierService::class.java)
    }

    override fun verify(token: String): Map<String, Any> {

        val verifier = JWT.require(Algorithm.HMAC512(key))
            .withIssuer(issuer)
            .build()

        val jwt = try {
            verifier.verify(token)
        } catch (e: JWTVerificationException) {
            log.error("Unable to verify ${e.localizedMessage}")
            null
        } catch (e: UnsupportedEncodingException) {
            log.error("Unable to verify ${e.localizedMessage}")
            null
        } ?: return emptyMap()

        val matched = jwt.audience.filter { it == audience }
        if (matched.isEmpty()) {
            log.error("Token is not intended for this server")
            return emptyMap()
        }

        val mappings = TokenUtil.mergeMappings(configured = claimProperties.claims, default = getDefaultClaimMapping())
        return parseClaims(jwt, mappings)
    }

    private fun getDefaultClaimMapping(): List<ClaimMapping> {

        val claims = mutableListOf<ClaimMapping>()

        claims.add(mappingOf(claim = "app", attribute = "app"))
        claims.add(mappingOf(claim = "uid", attribute = "user"))
        claims.add(mappingOf(claim = "name", attribute = "name"))
        claims.add(mappingOf(claim = "user", attribute = "email"))
        claims.add(mappingOf(claim = "tenant", attribute = "tenant"))
        claims.add(mappingOf(claim = "store", attribute = "store"))
        claims.add(mappingOf(claim = "client", attribute = "client"))

        return claims
    }
}
