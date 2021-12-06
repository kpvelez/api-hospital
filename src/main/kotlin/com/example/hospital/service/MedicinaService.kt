package com.example.hospital.service

import com.example.hospital.model.Medicina
import com.example.hospital.repository.MedicinaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class MedicinaService {
    @Autowired
    lateinit var medicinaRepository: MedicinaRepository


    fun list(): List<Medicina> {

        return medicinaRepository.findAll()

    }

    fun save (@RequestBody medicina: Medicina): Medicina {

      try {

          val response1 = medicinaRepository.findById(medicina.pacienteIdPaciente)
              ?: throw Exception("El ID ${medicina.pacienteIdPaciente}  no existe")

          if(medicina.dosis.equals("") || medicina.medicamento.equals("") || medicina.via.equals("")
              || medicina.fecha.equals("")){
              throw Exception("Uno de los campos esta vacio")
          }

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
               ?: throw Exception("El ID ${medicina.idmedicina}  no existe")


           val response1 = medicinaRepository.findById(medicina.pacienteIdPaciente)
               ?: throw Exception("El ID ${medicina.pacienteIdPaciente}  no existe")

           if (medicina.dosis.equals("") || medicina.medicamento.equals("") || medicina.via.equals("")
               || medicina.fecha.equals("")) {

               throw Exception("Uno de los campos esta vacio")
           }

           if(medicina.dosis!! < "0" && medicina.dosis!!  > "5" ){
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
           if (medicina.dosis.equals("")){
               throw Exception("El campo dosis esta vacio")
           }
           val response = medicinaRepository.findById(medicina.idmedicina)
               ?: throw Exception("El ID ${medicina.idmedicina}  no existe")


           response.apply {
               this.dosis=medicina.dosis
           }
           return medicinaRepository.save(response)
       } catch(ex: Exception){
           throw ResponseStatusException(
               HttpStatus.NOT_FOUND, ex.message, ex)
       }
    }


    fun delete (idMedicina:Long): Boolean{
        medicinaRepository.deleteById(idMedicina)
        return true
    }

}