package com.example.demo.resource

import com.example.demo.data.Slot
import com.example.demo.service.SlotService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/slot")
class SlotController {

    companion object {
        private val log = LoggerFactory.getLogger(SlotController::class.java)
    }

    @Autowired
    lateinit var slotService: SlotService

    @GetMapping
    fun getAllSlots(): List<Slot> {
        log.info("getAllSlots called")
        val result = slotService.getAllSlots()
        log.info("result count ${result.size}")
        return result
    }

    @GetMapping("/{id}")
    fun getSlotByID(@PathVariable("id") id: Long): Slot {
        log.info("getSlotByID called")
        return slotService.getSlotByID(id)
    }

    @PostMapping
    fun createSlot(@RequestBody slot: Slot) {
        log.info("createSlot called")
        slotService.createSlot(slot)
    }

}
