package com.example.demo.service

import com.example.demo.dao.CityRepository
import com.example.demo.data.City
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CityService {

    companion object {
        private val log = LoggerFactory.getLogger(CityService::class.java)
    }

    @Autowired
    lateinit var cityRepository: CityRepository

    fun getAllCities(): List<City> {
        log.info("getAllCities called")
        val result = cityRepository.getAll()
        log.info("result count ${result.size}")
        return cityRepository.getAll()
    }

    fun getCityByID(id : Long): City {
        log.info("getCityByID called")
        return cityRepository.getOne(id)
    }

    fun createCity(city: City) {
        log.info("createCity called")
        cityRepository.save(city)
    }

}
