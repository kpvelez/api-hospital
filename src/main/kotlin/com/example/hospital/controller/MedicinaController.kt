package com.example.hospital.controller

import com.example.hospital.model.Medicina
import com.example.hospital.service.MedicinaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/medicina")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class MedicinaController {

    @Autowired
    lateinit var medicinaService: MedicinaService

    @GetMapping
    fun list(): List<Medicina>{
        return medicinaService.list()

    }
    @PostMapping
    fun save (@RequestBody medicina: Medicina): Medicina {
        return medicinaService.save(medicina)
    }

    @PutMapping
    fun update(@RequestBody medicina: Medicina): Medicina {
        return medicinaService.update(medicina)

    }

    @PatchMapping
    fun updateDosis (@RequestBody medicina: Medicina): Medicina {
        return medicinaService.updateDosis(medicina)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") idMedicina: Long):Boolean{
        return medicinaService.delete(idMedicina)
    }
}
