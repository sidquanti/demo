package com.example.demo.dao

import com.example.demo.data.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p")
    fun getAll(): List<Payment>

}
