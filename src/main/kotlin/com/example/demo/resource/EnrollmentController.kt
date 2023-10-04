package com.example.demo.resource

import com.example.demo.auth.annotation.Secured
import com.example.demo.data.Enrollment
import com.example.demo.service.EnrollmentService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/enrollment")
class EnrollmentController {

    companion object {
        private val log = LoggerFactory.getLogger(EnrollmentController::class.java)
    }

    @Autowired
    lateinit var enrollmentService: EnrollmentService

    @GetMapping
    @Secured
    fun getAllEnrollments(@RequestAttribute("email") email : String,
        @RequestParam(required = true, value = "libraryId") libraryId: Long): List<Enrollment> {
        log.info("getAllEnrollments called $libraryId by user email $email")
        val result = enrollmentService.getAllEnrollments(libraryId, email)
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getEnrollmentByID(@PathVariable("id") id: Long): Enrollment {
        log.info("getEnrollmentByID called")
        return enrollmentService.getEnrollmentByID(id)
    }

    @GetMapping("/mine")
    @Secured
    fun getUsersEnrollment(@RequestAttribute("email") email : String): List<Enrollment> {
        log.info("getUsersEnrollment called")
        return enrollmentService.getUsersEnrollment(email)
    }

    @PostMapping
    //@Secured
    fun createEnrollment(@RequestBody enrollment: Enrollment) {
        log.info("createEnrollment called")
        enrollmentService.createEnrollment(enrollment)
    }

}
