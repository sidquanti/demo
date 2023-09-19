package com.example.demo.service

import com.example.demo.dao.UserRepository
import com.example.demo.data.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {

    companion object {
        private val log = LoggerFactory.getLogger(UserService::class.java)
    }

    @Autowired
    lateinit var userRepository: UserRepository

    fun getAllUsers(): List<User> {
        log.info("getAllUsers called")
        val result = userRepository.getAll()
        log.info("result count ${result.size}")
        return result
    }

    fun getUserByID(id: Long): User {
        log.info("getUserByID called")
        return userRepository.getOne(id)
    }

    fun getUserByEmail(email: String): User? {
        log.info("getUserByEmail called $email")
        return userRepository.getUserByEmail(email)
    }

    @Transactional
    fun createUser(user: User): User {
        log.info("createUser called")
        return userRepository.save(user)
    }

}
