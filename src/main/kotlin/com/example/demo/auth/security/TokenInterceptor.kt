package com.example.demo.auth.security

import com.example.demo.auth.annotation.Secured
import com.example.demo.auth.model.AuthenticationToken
import com.example.demo.auth.service.TokenVerifier
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(0)
class TokenInterceptor() : HandlerInterceptor {

    @Autowired
    private lateinit var tokenVerifier: TokenVerifier

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val auth = request.getHeader(HttpHeaders.AUTHORIZATION)
        var validated = false
        if (auth != null) {
            val verified = tokenVerifier.verify(auth, request)
            if (verified == null) {
                log.error("Error in verifying JWT")
                response.status = HttpStatus.UNAUTHORIZED.value()
                return false
            }

            // set security context for feign clients
            val authentication = AuthenticationToken(verified.userId, verified.tenant, verified.name, verified.app)
            SecurityContextHolder.getContext().authentication = authentication
            validated = true
        }

        if (handler !is HandlerMethod || !handler.method.isAnnotationPresent(Secured::class.java))
            return super.preHandle(request, response, handler)

        val secured = handler.method.getAnnotation(Secured::class.java)
        if (secured.scopes.isEmpty()) {
            if(validated) return true

            response.status = HttpStatus.UNAUTHORIZED.value()
            return false
        }
        return true
    }

    companion object {
        private val log = LoggerFactory.getLogger(TokenInterceptor::class.java)
    }
}
