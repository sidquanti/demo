package com.example.demo.service

import com.example.demo.dao.LibraryRepository
import com.example.demo.data.Library
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LibraryService {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryService::class.java)
    }

    @Autowired
    lateinit var libraryRepository: LibraryRepository

    fun getAllLibraries(): List<Library> {
        return libraryRepository.getAll()
    }

    fun getAllLibrariesByCityId(cityId: Long): List<Library> {
        return libraryRepository.getAllLibrariesByCityId(cityId)
    }

    fun getLibraryByID(id: Long): Library {
        log.info("getLibraryByID called")
        return libraryRepository.getOne(id)
    }

    fun createLibrary(library: Library) {
        log.info("createLibrary called")
        libraryRepository.save(library)
    }

}
