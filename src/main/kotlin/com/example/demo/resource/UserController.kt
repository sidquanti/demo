package com.example.demo.resource

import com.example.demo.auth.annotation.Secured
import com.example.demo.data.User
import com.example.demo.exception.RequestException
import com.example.demo.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {

    companion object {
        private val log = LoggerFactory.getLogger(UserController::class.java)
    }

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getAllUsers(): List<User> {
        log.info("getAllUsers called")
        val result = userService.getAllUsers()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/profile")
    @Secured
    fun getUserProfile(@RequestAttribute("email") email : String): User? {
        log.info("getUserProfile called $email")
        return userService.getUserByEmail(email)
    }

    @GetMapping("/{id}")
    fun getUserByID(@PathVariable("id") id: Long): User {
        log.info("getUserByID called")
        return userService.getUserByID(id)
    }

    @PutMapping("/{id}")
    fun updateUserByID(@PathVariable("id") id: Long, @RequestBody user: User) {
        log.info("updateUserByID called for $id")
        if (user.mobile.isNullOrEmpty()) throw RequestException("mobile is empty")
        return userService.updateUserByID(id, user.mobile!!)
    }

    @PostMapping
    fun createUser(@RequestBody user: User) {
        log.info("createUser called")
        userService.createUser(user)
    }

}
