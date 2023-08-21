package com.example.demo.data

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "slot")
data class Slot(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "library_id")
    var libraryId: Long?,

    @Column(name = "start_time")
    var startTime: String?,

    @Column(name = "end_time")
    var endTime: String?,

    @Column(name = "slot_capacity")
    var slotCapacity: Int?,

    @Column(name = "slot_price")
    var slotPrice: Double?,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ActiveInactive?,

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
