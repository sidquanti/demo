package com.example.demo.service

import com.example.demo.dao.EnrollmentRepository
import com.example.demo.data.Enrollment
import com.example.demo.data.User
import com.example.demo.exception.RequestException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EnrollmentService {

    companion object {
        private val log = LoggerFactory.getLogger(EnrollmentService::class.java)
    }

    @Autowired
    lateinit var enrollmentRepository: EnrollmentRepository

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var slotService: SlotService

    @Autowired
    lateinit var libraryService: LibraryService

    fun getAllEnrollments(libraryId: Long, userEmail : String): List<Enrollment> {
        log.info("getAllEnrollments called")

        val user =  userService.getUserByEmail(userEmail) ?: throw RequestException("User not found for $userEmail")
        val libraryList = libraryService.getAllLibrariesByUserIdOrEmail(user.id!!, userEmail)

        if (libraryId !in libraryList.map { it.id!! }) throw RequestException("Only admin can check all enrollments of library $libraryId")

        val result = enrollmentRepository.getAll(libraryId)

        result.forEach {
            val user = userService.getUserByID(it.userId!!)
            it.name = user.name
            it.mobile = user.mobile
            it.email = user.email
        }
        log.info("result count ${result.size}")
        return result
    }

    fun getEnrollmentByID(id: Long): Enrollment {
        log.info("getEnrollmentByID called")
        return enrollmentRepository.getOne(id)
    }

    fun getUsersEnrollment(email: String): List<Enrollment> {
        log.info("getUsersEnrollment called for $email")
        val user =  userService.getUserByEmail(email) ?: throw RequestException("User not found for $email")

        val result = enrollmentRepository.getAllByUserId(user!!.id!!)

        result.forEach {
            val user = userService.getUserByID(it.userId!!)
            it.name = user.name
            it.mobile = user.mobile
            it.email = user.email
            it.libraryName = libraryService.getLibraryByID(it.libraryId!!).name
        }

        log.info("result count ${result.size}")
        return result
    }

    fun createEnrollment(enrollment: Enrollment) {
        log.info("createEnrollment called")
        if(enrollment.slotId == null && enrollment.libraryId == null)  throw RequestException("slotId or libraryId is mandatory")
        if(enrollment.userId == null && enrollment.email.isNullOrEmpty())  throw RequestException("studentId or email is mandatory")

        if(enrollment.libraryId == null){
            enrollment.libraryId = slotService.getSlotByID(enrollment.slotId!!).libraryId
        }
        if(enrollment.userId == null){
            var user =  userService.getUserByEmail(enrollment.email!!)
            if (user == null){
                user = userService.createUser(User(name = enrollment.name, mobile = enrollment.mobile,
                    email = enrollment.email, student = true, createdOn = LocalDateTime.now()))
            }
            enrollment.userId = user.id
        }
        // TODO add entry in payment table

        enrollmentRepository.save(enrollment)
    }

}
