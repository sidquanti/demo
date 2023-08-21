package com.example.demo.dao

import com.example.demo.data.Library
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LibraryRepository : JpaRepository<Library, Long> {

    @Query("SELECT l FROM Library l")
    fun getAll(): List<Library>

    @Query("SELECT l FROM Library l where l.cityId = :cityId")
    fun getAllLibrariesByCityId(@Param("cityId") cityId: Long): List<Library>


}
