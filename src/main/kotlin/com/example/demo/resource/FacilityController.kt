package com.example.demo.resource

import com.example.demo.data.Facility
import com.example.demo.service.FacilityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/facility")
class FacilityController {

    companion object {
        private val log = LoggerFactory.getLogger(FacilityController::class.java)
    }

    @Autowired
    lateinit var facilityService: FacilityService

    @GetMapping
    fun getAllFacilities(): List<Facility> {
        log.info("getAllFacilities called")
        val result = facilityService.getAllFacilities()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getFacilityByID(@PathVariable("id") id: Long): Facility {
        log.info("getFacilityByID called")
        return facilityService.getFacilityByID(id)
    }

    @PostMapping
    fun createFacility(@RequestBody facility: Facility) {
        log.info("createFacility called")
        facilityService.createFacility(facility)
    }

}
