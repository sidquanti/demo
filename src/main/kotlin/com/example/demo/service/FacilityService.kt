package com.example.demo.service

import com.example.demo.dao.FacilityRepository
import com.example.demo.data.Facility
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FacilityService {

    companion object {
        private val log = LoggerFactory.getLogger(FacilityService::class.java)
    }

    @Autowired
    lateinit var facilityRepository: FacilityRepository

    fun getAllFacilities(): List<Facility> {
        log.info("getAllFacilities called")
        val result = facilityRepository.getAll()
        log.info("result count ${result.size}")
        return result
    }

    fun getFacilityByID(id: Long): Facility {
        log.info("getFacilityByID called")
        return facilityRepository.getOne(id)
    }

    fun createFacility(facility: Facility) {
        log.info("createFacility called")
        facilityRepository.save(facility)
    }

}
