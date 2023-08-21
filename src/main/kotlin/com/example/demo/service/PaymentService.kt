package com.example.demo.service

import com.example.demo.dao.PaymentRepository
import com.example.demo.data.Payment
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PaymentService {

    companion object {
        private val log = LoggerFactory.getLogger(PaymentService::class.java)
    }

    @Autowired
    lateinit var paymentRepository: PaymentRepository

    fun getAllPayments(): List<Payment> {
        log.info("getAllPayments called")
        val result = paymentRepository.getAll()
        log.info("result count ${result.size}")
        return result
    }

    fun getPaymentByID(id: Long): Payment {
        log.info("getPaymentByID called")
        return paymentRepository.getOne(id)
    }

    fun createPayment(payment: Payment) {
        log.info("createPayment called")
        paymentRepository.save(payment)
    }

}
