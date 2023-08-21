package com.example.demo.service

import com.example.demo.dao.LibraryFacilityRepository
import com.example.demo.data.LibraryFacility
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LibraryFacilityService {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryFacilityService::class.java)
    }

    @Autowired
    lateinit var libraryFacilityRepository: LibraryFacilityRepository

    fun getAllLibraryFacilities(): List<LibraryFacility> {
        log.info("getAllLibraryFacilities called")
        val result = libraryFacilityRepository.getAll()
        log.info("result count ${result.size}")
        return result
    }

    fun getLibraryFacilityByID(id: Long): LibraryFacility {
        log.info("getLibraryFacilityByID called")
        return libraryFacilityRepository.getOne(id)
    }

    fun createLibraryFacility(libraryFacility: LibraryFacility) {
        log.info("createLibraryFacility called")
        libraryFacilityRepository.save(libraryFacility)
    }

}
