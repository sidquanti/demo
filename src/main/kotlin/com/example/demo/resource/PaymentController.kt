package com.example.demo.resource

import com.example.demo.data.Payment
import com.example.demo.service.PaymentService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payment")
class PaymentController {

    companion object {
        private val log = LoggerFactory.getLogger(PaymentController::class.java)
    }

    @Autowired
    lateinit var paymentService: PaymentService

    @GetMapping
    fun getAllPayments(): List<Payment> {
        log.info("getAllPayments called")
        val result = paymentService.getAllPayments()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getPaymentByID(@PathVariable("id") id: Long): Payment {
        log.info("getPaymentByID called")
        return paymentService.getPaymentByID(id)
    }

    @PostMapping
    fun createPayment(@RequestBody payment: Payment) {
        log.info("createPayment called")
        paymentService.createPayment(payment)
    }

}
