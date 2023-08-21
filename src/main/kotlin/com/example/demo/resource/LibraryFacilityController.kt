package com.example.demo.resource

import com.example.demo.data.LibraryFacility
import com.example.demo.service.LibraryFacilityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/library-facility")
class LibraryFacilityController {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryFacilityController::class.java)
    }

    @Autowired
    lateinit var libraryFacilityService: LibraryFacilityService

    @GetMapping
    fun getAllLibraryFacilities(): List<LibraryFacility> {
        log.info("getAllLibraryFacilities called")
        val result = libraryFacilityService.getAllLibraryFacilities()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getLibraryFacilityByID(@PathVariable("id") id: Long): LibraryFacility {
        log.info("getLibraryFacilityByID called")
        return libraryFacilityService.getLibraryFacilityByID(id)
    }

    @PostMapping
    fun createLibraryFacility(@RequestBody libraryFacility: LibraryFacility) {
        log.info("createLibraryFacility called")
        libraryFacilityService.createLibraryFacility(libraryFacility)
    }

}
