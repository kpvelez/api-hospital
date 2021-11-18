package com.example.hospital.service

import com.example.hospital.model.Doctor
import com.example.hospital.repository.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody


@Service
class DoctorService {
    @Autowired
    lateinit var doctorRepository: DoctorRepository


    fun list(): List<Doctor> {

        return doctorRepository.findAll()

    }

    fun save (@RequestBody doctor: Doctor): Doctor {
        return doctorRepository.save(doctor)

    }

    fun update (@RequestBody doctor: Doctor): Doctor {
        return doctorRepository.save(doctor)
    }

    fun updateApellido (doctor: Doctor): Doctor {
        val response = doctorRepository.findById(doctor.iddoctor)
            ?: throw Exception()
        response.apply {
            this.apellido=doctor.apellido
        }
        return doctorRepository.save(response)
    }

    fun delete (idDoctor:Long): Boolean{
        doctorRepository.deleteById(idDoctor)
        return true
    }

}