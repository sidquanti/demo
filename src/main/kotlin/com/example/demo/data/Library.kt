package com.example.demo.data

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "library")
data class Library(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String?,

    @Column(name = "address")
    var address: String?,

    @Column(name = "opening_time")
    var openingTime: String?,

    @Column(name = "closing_time")
    var closingTime: String?,

    @Column(name = "total_capacity")
    var totalCapacity: Int?,

    @Column(name = "mobile")
    var mobile: String?,

    @Column(name = "email")
    var email: String?,

    @Column(name = "gstin")
    var gstin: String?,

    @Column(name = "city_id")
    var cityId: Long? = null,

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
    var updatedBy: String?,

    @Column(name = "user_id")
    var userId: Long?,

    @OneToMany(cascade= [CascadeType.ALL], mappedBy = "libraryId", fetch = FetchType.EAGER)
    val libraryFacilities : MutableList<LibraryFacility> = mutableListOf(),

)