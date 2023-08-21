package com.example.demo.service

import com.example.demo.dao.LibraryImageRepository
import com.example.demo.data.LibraryImage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LibraryImageService {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryImageService::class.java)
    }

    @Autowired
    lateinit var libraryImageRepository: LibraryImageRepository

    fun getAllLibraryImages(libraryId: Long?): List<LibraryImage> {
        log.info("getAllLibraryImages called")
        val result = libraryImageRepository.getAll(libraryId)
        log.info("result count ${result.size}")
        return result
    }

    fun getLibraryImageByID(id: Long): LibraryImage {
        log.info("getLibraryImageByID called")
        return libraryImageRepository.getOne(id)
    }

    fun createLibraryImage(libraryImage: LibraryImage) {
        log.info("createLibraryImage called")
        libraryImageRepository.save(libraryImage)
    }

}
