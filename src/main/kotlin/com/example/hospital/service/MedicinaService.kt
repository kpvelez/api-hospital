package com.example.hospital.service

import com.example.hospital.model.Medicina
import com.example.hospital.repository.MedicinaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody


@Service
class MedicinaService {
    @Autowired
    lateinit var medicinaRepository: MedicinaRepository


    fun list(): List<Medicina> {

        return medicinaRepository.findAll()

    }

    fun save (@RequestBody medicina: Medicina): Medicina {
        return medicinaRepository.save(medicina)

    }

    fun update (@RequestBody medicina: Medicina): Medicina {
        return medicinaRepository.save(medicina)
    }

    fun updateDosis (medicina: Medicina): Medicina {
        val response = medicinaRepository.findById(medicina.idmedicina)
            ?: throw Exception()
        response.apply {
            this.dosis=medicina.dosis
        }
        return medicinaRepository.save(response)
    }

    fun delete (idMedicina:Long): Boolean{
        medicinaRepository.deleteById(idMedicina)
        return true
    }

}