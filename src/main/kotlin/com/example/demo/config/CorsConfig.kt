package com.example.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class CorsConfig : WebMvcConfigurer {

    @Autowired
    private lateinit var tokenInterceptor: HandlerInterceptor

    // cors
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "http://localhost:8080",
                "https://cricget.com", "https://cricget.com:3000", "https://cricget.com:8080",
                "http://cricget.com", "http://cricget.com:3000", "http://cricget.com:8080",
                "https://studyvenue.in", "https://studyvenue.in:3000", "https://studyvenue.in:8080",
                "http://studyvenue.in", "http://studyvenue.in:3000", "http://studyvenue.in:8080") // Add your frontend URL here
            .allowedMethods("GET", "POST", "PUT", "DELETE")
    }

    // AuthWebConfig
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenInterceptor)
    }
}
