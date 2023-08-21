package com.example.demo.data

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "sample")
data class Sample(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "data")
    var data: String?,

    @Column(name = "quantity")
    var quantity: Int?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "dp_updated_at")
    var dpUpdatedAt: LocalDateTime? = LocalDateTime.now(),

    )
