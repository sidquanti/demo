package com.example.demo.data

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "library_image")
data class LibraryImage(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "library_id")
    var libraryId: Long?,

    @Column(name = "facility_id")
    var facilityId: Long?,

    @Column(name = "image_url")
    var imageUrl: String?,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ActiveInactive?,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_on")
    var createdOn: LocalDateTime?,

    @Column(name = "created_by")
    var createdBy: String?

)
