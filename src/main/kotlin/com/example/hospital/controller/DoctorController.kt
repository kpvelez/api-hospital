package com.example.hospital.controller

import com.example.hospital.model.Doctor
import com.example.hospital.service.DoctorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

    @RestController
    @RequestMapping("/doctor")
    @CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

    class DoctorController {

        @Autowired
        lateinit var doctorService: DoctorService

        @GetMapping
        fun list(): List<Doctor>{
            return doctorService.list()

        }
        @PostMapping
        fun save (@RequestBody doctor: Doctor): Doctor {
            return doctorService.save(doctor)
        }

        @PutMapping
        fun update(@RequestBody doctor: Doctor): Doctor {
            return doctorService.update(doctor)

        }

        @PatchMapping
        fun updateApeliido (@RequestBody doctor: Doctor): Doctor{
            return doctorService.updateApellido(doctor)
        }

        @DeleteMapping("/delete/{id}")
        fun delete (@PathVariable("id") idDoctor: Long):Boolean{
            return doctorService.delete(idDoctor)
        }
    }
