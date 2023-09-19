package com.example.demo.auth.model

import com.example.demo.auth.model.ClaimMapping
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
@ConfigurationProperties(prefix = "app.security.token")
class ClaimProperties {

    var claims: List<ClaimMapping> = ArrayList()
}
