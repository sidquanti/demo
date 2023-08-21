package com.example.demo.resource

import com.example.demo.data.Sample
import com.example.demo.service.SampleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sample")
class SampleController {

    companion object {
        private val log = LoggerFactory.getLogger(SampleController::class.java)
    }

    @Autowired
    lateinit var sampleService: SampleService

    @GetMapping
    fun getAllSamples(): List<Sample> {
        log.info("getAllSamples called")
        val result = sampleService.getAllSamples()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getSampleByID(@PathVariable("id") id: Long): Sample {
        log.info("getSampleByID called")
        return sampleService.getSampleByID(id)
    }

    @PostMapping
    fun createSample(@RequestBody sample:Sample) {
        log.info("createSample called")
        sampleService.createSample(sample)
    }

}
