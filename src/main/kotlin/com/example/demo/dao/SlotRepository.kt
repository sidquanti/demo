package com.example.demo.dao

import com.example.demo.data.Slot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SlotRepository : JpaRepository<Slot, Long> {

    @Query("SELECT s FROM Slot s")
    fun getAll(): List<Slot>

}
