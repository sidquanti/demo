package com.example.demo.service

import com.example.demo.dao.SlotRepository
import com.example.demo.data.Slot
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SlotService {

    companion object {
        private val log = LoggerFactory.getLogger(SlotService::class.java)
    }

    @Autowired
    lateinit var slotRepository: SlotRepository

    fun getAllSlots(): List<Slot> {
        log.info("getAllSlots called")
        val result = slotRepository.getAll()
        log.info("result count ${result.size}")
        return result
    }

    fun getSlotByID(id: Long): Slot {
        log.info("getSlotByID called")
        return slotRepository.getOne(id)
    }

    fun createSlot(slot: Slot) {
        log.info("createSlot called")
        slotRepository.save(slot)
    }

}
