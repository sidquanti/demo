package com.example.demo.data

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "payment")
data class Payment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "enrollment_id")
    var enrollmentId: Long?,

    @Column(name = "student_id")
    var studentId: Long?,

    @Column(name = "amount")
    var amount: Double?,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: PendingCompleted?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_on")
    var createdOn: LocalDateTime?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "updated_on")
    var updatedOn: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "created_by")
    var createdBy: String?,

    @Column(name = "updated_by")
    var updatedBy: String?

)
