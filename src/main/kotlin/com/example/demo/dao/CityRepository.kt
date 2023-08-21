package com.example.demo.dao

import com.example.demo.data.City
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : JpaRepository<City, Long> {

    @Query("SELECT c FROM City c")
    fun getAll(): List<City>

}
