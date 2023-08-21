package com.example.demo.service

import com.example.demo.dao.SampleRepository
import com.example.demo.data.Sample
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SampleService {

    companion object {
        private val log = LoggerFactory.getLogger(SampleService::class.java)
    }

    @Autowired
    lateinit var sampleRepository: SampleRepository

    fun getAllSamples(): List<Sample> {
        log.info("sample called")
        val result = sampleRepository.getAll()
        log.info("result count ${result.size}")
        return sampleRepository.getAll()
    }

    fun getSampleByID(id : Long): Sample {
        log.info("getSampleByID called")
        return sampleRepository.getOne(id)
    }

    fun createSample(sample: Sample) {
        log.info("createSample called")
        sampleRepository.save(sample)
    }

}