package com.example.demo.dao

import com.example.demo.data.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s")
    fun getAll(): List<Student>

    @Query("SELECT s FROM Student s where s.mobile = :mobile")
    fun getStudentByMobile(@Param("mobile") mobile: String): Student?

}
