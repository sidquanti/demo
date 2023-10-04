package com.example.demo.resource

import com.example.demo.auth.annotation.Secured
import com.example.demo.data.Library
import com.example.demo.exception.RequestException
import com.example.demo.service.LibraryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.regex.Pattern

@RestController
@RequestMapping("/library")
class LibraryController {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryController::class.java)
    }

    @Autowired
    lateinit var libraryService: LibraryService

    @GetMapping("/users")
    @Secured
    fun getAllLibrariesOfUser(@RequestAttribute("email") email : String): List<Library> {
        log.info("getMyLibraries called for $email")
        val result = libraryService.getAllLibrariesOfUser(email)
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/mobile/{mobile}")
    fun getAllLibrariesByMobileNumber(@PathVariable("mobile") mobile: String): List<Library> {
        log.info("getAllLibrariesByMobileNumber called for mobile $mobile")
        val result = libraryService.getAllLibrariesByMobileNumber(mobile)
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/city/{cityId}")
    fun getAllLibrariesByCityId(@PathVariable("cityId") cityId: Long): List<Library> {
        log.info("getAllLibrariesByCityId called for cityId $cityId")
        val result = libraryService.getAllLibrariesByCityId(cityId)
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getLibraryByID(@PathVariable("id") id: Long): Library {
        log.info("getLibraryByID called")
        return libraryService.getLibraryByID(id)
    }

    @PostMapping
    @Secured
    fun createLibrary(@RequestBody library: Library,
                      @RequestAttribute("email") email : String) {
        log.info("createLibrary called")
        if(!library.email.isNullOrEmpty() && !isValidMail(library.email!!)) throw RequestException("Email id not valid ${library.email}")
        if(!library.mobile.isNullOrEmpty() && !isValidMobile(library.mobile!!)) throw RequestException("Mobile no. is not valid ${library.mobile}")

        libraryService.createLibrary(library, email)
    }

    private fun isValidMail(email: String): Boolean {
        val EMAIL_STRING = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        return Pattern.compile(EMAIL_STRING).matcher(email).matches()
    }

    private fun isValidMobile(phone: String): Boolean {
        val pattern = "^[0-9]{10}$"
        return phone.matches(Regex(pattern))
    }

}
