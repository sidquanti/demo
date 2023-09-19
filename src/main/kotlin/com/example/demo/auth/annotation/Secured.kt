package com.example.demo.auth.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Secured(val scopes: Array<String> = [])
