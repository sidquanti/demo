package com.example.demo.dao

import com.example.demo.data.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u")
    fun getAll(): List<User>

    @Query("SELECT u FROM User u where u.email = :email")
    fun getUserByEmail(@Param("email") email: String): User?

}
