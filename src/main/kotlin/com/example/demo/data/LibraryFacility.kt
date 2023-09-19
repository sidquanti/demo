package com.example.demo.data

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "library_facility")
data class LibraryFacility(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "library_id")
    var libraryId: Library,

    @ManyToOne
    @JoinColumn(name = "facility_id")
    var facility: Facility,

    @Column(name = "included")
    var included: Boolean?,

    @Column(name = "extra_price")
    var extraPrice: Double?,

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
