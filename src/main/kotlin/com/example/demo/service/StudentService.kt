package com.example.demo.service

import com.example.demo.dao.StudentRepository
import com.example.demo.data.Student
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService {

    companion object {
        private val log = LoggerFactory.getLogger(StudentService::class.java)
    }

    @Autowired
    lateinit var studentRepository: StudentRepository

    fun getAllStudents(): List<Student> {
        log.info("getAllStudents called")
        val result = studentRepository.getAll()
        log.info("result count ${result.size}")
        return result
    }

    fun getStudentByID(id: Long): Student {
        log.info("getStudentByID called")
        return studentRepository.getOne(id)
    }

    fun getStudentByMobile(mobile: String): Student? {
        log.info("getStudentByMobile called $mobile")
        return studentRepository.getStudentByMobile(mobile)
    }

    fun createStudent(student: Student): Student {
        log.info("createStudent called")
        return studentRepository.save(student)
    }

}
