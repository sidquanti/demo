package com.example.demo.data

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "enrollment")
data class Enrollment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "library_id")
    var libraryId: Long?,

    @Column(name = "student_id")
    var studentId: Long?,

    @Column(name = "slot_id")
    var slotId: Long?,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ActiveInactive?,

    @Column(name = "exam_name")
    var examName: String?,

    @Column(name = "total_capacity")
    var totalCapacity: Int?,

    @Column(name = "remark")
    var remark: String?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "start_date")
    var startDate: LocalDateTime?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "end_date")
    var endDate: LocalDateTime?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_on")
    var createdOn: LocalDateTime?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "updated_on")
    var updatedOn: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "created_by")
    var createdBy: String?,

    @Column(name = "updated_by")
    var updatedBy: String?,

    @Transient
    var name: String?,

    @Transient
    var mobile: String?,

    @Transient
    var email: String?



)