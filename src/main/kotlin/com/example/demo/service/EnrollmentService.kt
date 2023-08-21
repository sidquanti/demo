package com.example.demo.service

import com.example.demo.dao.EnrollmentRepository
import com.example.demo.data.Enrollment
import com.example.demo.data.Student
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
    lateinit var studentService: StudentService

    @Autowired
    lateinit var slotService: SlotService

    fun getAllEnrollments(libraryId: Long?): List<Enrollment> {
        log.info("getAllEnrollments called")
        val result = enrollmentRepository.getAll(libraryId)
        log.info("result count ${result.size}")
        return result
    }

    fun getEnrollmentByID(id: Long): Enrollment {
        log.info("getEnrollmentByID called")
        return enrollmentRepository.getOne(id)
    }

    fun createEnrollment(enrollment: Enrollment) {
        log.info("createEnrollment called")
        if(enrollment.slotId == null && enrollment.libraryId == null)  throw RuntimeException("slotId or libraryId is mandatory")
        if(enrollment.studentId == null && enrollment.mobile.isNullOrEmpty())  throw RuntimeException("studentId or mobile is mandatory")

        if(enrollment.libraryId == null){
            enrollment.libraryId = slotService.getSlotByID(enrollment.slotId!!).libraryId
        }
        if(enrollment.studentId == null){
            var student =  studentService.getStudentByMobile(enrollment.mobile!!)
            if (student == null){
                enrollment.studentId = studentService.createStudent(
                    Student(name = enrollment.name, mobile = enrollment.mobile, email = enrollment.email,
                            userId = null, createdOn = LocalDateTime.now())).id
            }
        }

        // TODO add entry in payment table

        enrollmentRepository.save(enrollment)
    }

}
