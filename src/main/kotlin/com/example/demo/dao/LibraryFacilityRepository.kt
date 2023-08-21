package com.example.demo.dao

import com.example.demo.data.LibraryFacility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LibraryFacilityRepository : JpaRepository<LibraryFacility, Long> {

    @Query("SELECT lf FROM LibraryFacility lf")
    fun getAll(): List<LibraryFacility>

}
