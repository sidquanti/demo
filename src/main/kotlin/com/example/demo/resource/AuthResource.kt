package com.example.demo.resource

import com.example.demo.model.Credential
import com.example.demo.model.Profile
import com.example.demo.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthResource {

    companion object {
        private val log = LoggerFactory.getLogger(AuthResource::class.java)
    }

    @Autowired
    private lateinit var authService: AuthService


    @PostMapping("/signIn", consumes = [MediaType.APPLICATION_JSON_VALUE]
        , produces = [MediaType.APPLICATION_JSON_VALUE])
    fun signIn(@RequestBody credential: Credential): ResponseEntity<Profile> {

        val res = authService.signIn(credential) ?: return ResponseEntity(HttpStatus.UNAUTHORIZED)

        return ResponseEntity.ok(res)
    }
}
