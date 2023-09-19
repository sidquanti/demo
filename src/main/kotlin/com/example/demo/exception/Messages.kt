package com.example.demo.exception

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class Messages {

    @Autowired
    private lateinit var messageSource: MessageSource

    private lateinit var messageSourceAccessor: MessageSourceAccessor

    @PostConstruct
    fun init() {
        messageSourceAccessor = MessageSourceAccessor(messageSource, Locale.US)
    }

    fun get(code: String, args: Array<Any>, locale: Locale): String {
        return messageSourceAccessor.getMessage(code, args, locale)
    }
}
