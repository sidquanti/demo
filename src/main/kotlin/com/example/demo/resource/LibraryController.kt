package com.example.demo.resource

import com.example.demo.data.Library
import com.example.demo.service.LibraryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/library")
class LibraryController {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryController::class.java)
    }

    @Autowired
    lateinit var libraryService: LibraryService

    @GetMapping
    fun getAllLibraries(): List<Library> {
        log.info("getAllLibraries called")
        val result = libraryService.getAllLibraries()
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
    fun createLibrary(@RequestBody library: Library) {
        log.info("createLibrary called")
        libraryService.createLibrary(library)
    }

}
