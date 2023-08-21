package com.example.demo.data

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "facility")
data class Facility(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String?,

    @Column(name = "description")
    var description: String?,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ActiveInactive?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_on")
    var createdOn: LocalDateTime?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "updated_on")
    var updatedOn: LocalDateTime? = LocalDateTime.now()

    )
