package com.example.demo.dao

import com.example.demo.data.Enrollment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EnrollmentRepository : JpaRepository<Enrollment, Long> {

    @Query("SELECT e FROM Enrollment e where (:libraryId = null OR e.libraryId = :libraryId) order by endDate desc")
    fun getAll(@Param("libraryId") libraryId: Long): List<Enrollment>

    @Query("SELECT e FROM Enrollment e where e.userId = :userId order by endDate desc")
    fun getAllByUserId(@Param("userId") userId: Long): List<Enrollment>

}
