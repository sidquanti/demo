package com.example.demo.resource

import com.example.demo.data.City
import com.example.demo.service.CityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/city")
class CityController {

    companion object {
        private val log = LoggerFactory.getLogger(CityController::class.java)
    }

    @Autowired
    lateinit var cityService: CityService

    @GetMapping
    fun getAllCities(): List<City> {
        log.info("getAllCities called")
        val result = cityService.getAllCities()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getCityByID(@PathVariable("id") id: Long): City {
        log.info("getCityByID called")
        return cityService.getCityByID(id)
    }

    @PostMapping
    fun createCity(@RequestBody city:City) {
        log.info("createCity called")
        cityService.createCity(city)
    }

}
