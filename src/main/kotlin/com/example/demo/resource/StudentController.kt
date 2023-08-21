package com.example.demo.resource

import com.example.demo.data.Student
import com.example.demo.service.StudentService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController {

    companion object {
        private val log = LoggerFactory.getLogger(StudentController::class.java)
    }

    @Autowired
    lateinit var studentService: StudentService

    @GetMapping
    fun getAllStudents(): List<Student> {
        log.info("getAllStudents called")
        val result = studentService.getAllStudents()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getStudentByID(@PathVariable("id") id: Long): Student {
        log.info("getStudentByID called")
        return studentService.getStudentByID(id)
    }

    @PostMapping
    fun createStudent(@RequestBody student: Student) {
        log.info("createStudent called")
        studentService.createStudent(student)
    }

}
