package com.example.demo.dao

import com.example.demo.data.Sample
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SampleRepository : JpaRepository<Sample, Long> {

    @Query("SELECT s FROM Sample s")
    fun getAll(): List<Sample>

}