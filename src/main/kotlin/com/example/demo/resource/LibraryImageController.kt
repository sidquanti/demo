package com.example.demo.resource

import com.example.demo.data.LibraryImage
import com.example.demo.service.LibraryImageService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/library-image")
class LibraryImageController {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryImageController::class.java)
    }

    @Autowired
    lateinit var libraryImageService: LibraryImageService

    @GetMapping
    fun getAllLibraryImages(@RequestParam(required = false, value = "libraryId") libraryId: Long?): List<LibraryImage> {
        log.info("getAllLibraryImages called $libraryId")
        val result = libraryImageService.getAllLibraryImages(libraryId)
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getLibraryImageByID(@PathVariable("id") id: Long): LibraryImage {
        log.info("getLibraryImageByID called")
        return libraryImageService.getLibraryImageByID(id)
    }

    @PostMapping
    fun createLibraryImage(@RequestBody libraryImage: LibraryImage) {
        log.info("createLibraryImage called")
        libraryImageService.createLibraryImage(libraryImage)
    }

}
