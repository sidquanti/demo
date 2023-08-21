package com.example.demo.dao

import com.example.demo.data.LibraryImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LibraryImageRepository : JpaRepository<LibraryImage, Long> {

    @Query("SELECT l FROM LibraryImage l where (:libraryId = null OR l.libraryId = :libraryId)")
    fun getAll(@Param("libraryId") libraryId: Long?): List<LibraryImage>

}
