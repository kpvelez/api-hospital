package com.example.hospital.service

import com.example.hospital.model.Medicina
import com.example.hospital.repository.MedicinaRepository
import com.example.hospital.repository.PacienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class MedicinaService {
    @Autowired
    lateinit var medicinaRepository: MedicinaRepository

    @Autowired
    lateinit var pacienteRepository: PacienteRepository


    fun list(): List<Medicina> {

        return medicinaRepository.findAll()

    }

    fun save (@RequestBody medicina: Medicina): Medicina {

      try {

          val response1 = pacienteRepository.findById(medicina.pacienteIdPaciente)
              ?: throw Exception("El ID ${medicina.pacienteIdPaciente} de paciente no existe")



          medicina.dosis?.takeIf {it.trim().isNotEmpty()}
              ?: throw Exception("El campo dosis esta vacio")

          medicina.medicamento?.takeIf {it.trim().isNotEmpty()}
              ?: throw Exception("El campo medicamento esta vacio")

          medicina.fecha?.takeIf {it.trim().isNotEmpty()}
              ?: throw Exception("El campo fecha esta vacio")

          medicina.via?.takeIf {it.trim().isNotEmpty()}
              ?: throw Exception("El campo via esta vacio")

          if(medicina.dosis!! > "0" && medicina.dosis!!  < "5" ){
              throw Exception("La dosis no puede excederse a esa cantidad")
          } else {
              return medicinaRepository.save(medicina)
          }
      }  catch(ex: Exception){
          throw ResponseStatusException(
              HttpStatus.NOT_FOUND, ex.message, ex)
      }
    }


    fun update (@RequestBody medicina: Medicina): Medicina {
       try {


           val response = medicinaRepository.findById(medicina.idmedicina)
               ?: throw Exception("El ID ${medicina.idmedicina} de medicamento no existe")


           val response1 = pacienteRepository.findById(medicina.pacienteIdPaciente)
               ?: throw Exception("El ID ${medicina.pacienteIdPaciente} de paciente no existe")

           medicina.dosis?.takeIf {it.trim().isNotEmpty()}
               ?: throw Exception("El campo dosis esta vacio")

           medicina.medicamento?.takeIf {it.trim().isNotEmpty()}
               ?: throw Exception("El campo medicamento esta vacio")

           medicina.fecha?.takeIf {it.trim().isNotEmpty()}
               ?: throw Exception("El campo fecha esta vacio")

           medicina.via?.takeIf {it.trim().isNotEmpty()}
               ?: throw Exception("El campo via esta vacio")

           if(medicina.dosis!! >  "0" && medicina.dosis!!  < "5" ){
               throw Exception("La dosis no puede excederse a esa cantidad")
           } else {
               return medicinaRepository.save(medicina)
           }

       } catch(ex: Exception){
           throw ResponseStatusException(
               HttpStatus.NOT_FOUND, ex.message, ex)
       }
    }


    fun updateDosis (medicina: Medicina): Medicina {
       try {

           medicina.dosis?.takeIf {it.trim().isNotEmpty()}
               ?: throw Exception("El campo dosis esta vacio")

           val response = medicinaRepository.findById(medicina.idmedicina)
               ?: throw Exception("El ID ${medicina.idmedicina} de medicamento no existe")


           response.apply {
               this.dosis=medicina.dosis
           }
           return medicinaRepository.save(response)
       } catch(ex: Exception){
           throw ResponseStatusException(
               HttpStatus.NOT_FOUND, ex.message, ex)
       }
    }


    fun delete (idMedicina:Long?): Boolean{

        try {
            medicinaRepository.findById(idMedicina)
                ?: throw Exception("El ID de medicina no existe")
            medicinaRepository.deleteById(idMedicina!!)
            return true

        }catch(ex: Exception){
            throw Exception()
        }

    }

}