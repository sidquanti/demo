package com.example.demo.auth.service

import com.auth0.jwt.JWT
import com.example.demo.auth.model.VerifiedToken
import com.example.demo.auth.util.TokenUtil
import com.example.demo.auth.util.TokenUtil.Companion.empty
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class TokenVerifier(
    private val jwtTokenVerifierService: JwtTokenVerifierService
) {

    companion object {
        private val log = LoggerFactory.getLogger(TokenVerifier::class.java)
    }

    fun verify(header: String, request: HttpServletRequest): VerifiedToken? {

        val token = TokenUtil.parseToken(header)
        val jwt = JWT.decode(token)

        val claims = jwtTokenVerifierService.verify(token)
        if (claims.isEmpty()) {
            return null
        }

        claims.entries.forEach {
            request.setAttribute(it.key, it.value)
        }

        return VerifiedToken(
            valueOf(claims, "app").toString(),
            valueOf(claims, "user").toString(),
            valueOf(claims, "name").toString(),
            valueOf(claims, "email").toString(),
            valueOf(claims, "tenant").toString(),
            valueOf(claims, "store").toString(),
            valueOf(claims, "client").toString().toLongOrNull() ?: 0L
        )
    }

    private fun valueOf(claims: Map<String, Any>, key: String): Any {
        return claims[key] ?: empty
    }

    private fun listValueOf(claims: Map<String, Any>, key: String): List<String> {
        return if(claims.containsKey(key)){
            return claims[key] as List<String>
        }
        else emptyList()
    }
}
