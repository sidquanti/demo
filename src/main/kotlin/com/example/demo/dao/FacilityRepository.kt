package com.example.demo.dao

import com.example.demo.data.Facility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FacilityRepository : JpaRepository<Facility, Long> {

    @Query("SELECT f FROM Facility f")
    fun getAll(): List<Facility>

}
