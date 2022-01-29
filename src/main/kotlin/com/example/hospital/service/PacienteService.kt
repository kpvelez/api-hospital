package com.example.hospital.service

import com.example.hospital.model.Paciente
import com.example.hospital.repository.DoctorRepository
import com.example.hospital.repository.PacienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class PacienteService {

    @Autowired
    lateinit var pacienteRepository: PacienteRepository
    @Autowired
    lateinit var doctorRepository: DoctorRepository



    fun list(): List<Paciente> {

        return pacienteRepository.findAll()

    }

    fun save (@RequestBody paciente: Paciente): Paciente {

     try {

         val response = doctorRepository.findById(paciente.doctorIdDoctor)
             ?: throw Exception("El ID ${paciente.doctorIdDoctor} de doctor no existe")


         paciente.nombre?.takeIf { it.trim().isNotEmpty() }
             ?: throw Exception("El campo nombre esta vacio")

         paciente.apellido?.takeIf { it.trim().isNotEmpty() }
             ?: throw Exception("El campo apellido esta vacio")

         paciente.cedula?.takeIf { it.trim().isNotEmpty() }
             ?: throw Exception("El campo cedula esta vacio")

         paciente.enfermedad?.takeIf { it.trim().isNotEmpty() }
             ?: throw Exception("El campo enfermedad esta vacio")

         if (paciente.cedula!!.length == 11 ) {
             throw Exception("La cedula debe tener 10 digitos")

         } else {
             return pacienteRepository.save(paciente)
         }

     }   catch(ex: Exception){
         throw ResponseStatusException(
             HttpStatus.NOT_FOUND, ex.message, ex)

    }
    }

    fun update (@RequestBody paciente: Paciente): Paciente {

       try {

           val response = pacienteRepository.findById(paciente.idpaciente)
               ?: throw Exception("El ID ${paciente.idpaciente} de paciente no existe")


           val response1 = doctorRepository.findById(paciente.doctorIdDoctor)
               ?: throw Exception("El ID ${paciente.doctorIdDoctor} de doctor no existe")

           paciente.nombre?.takeIf { it.trim().isNotEmpty() }
               ?: throw Exception("El campo nombre esta vacio")

           paciente.apellido?.takeIf { it.trim().isNotEmpty() }
               ?: throw Exception("El campo apellido esta vacio")

           paciente.cedula?.takeIf { it.trim().isNotEmpty() }
               ?: throw Exception("El campo cedula esta vacio")

           paciente.enfermedad?.takeIf { it.trim().isNotEmpty() }
               ?: throw Exception("El campo enfermedad esta vacio")

           if (paciente.cedula!!.length == 11 ) {
               throw Exception("La cedula debe tener 10 digitos")
           } else{
               return pacienteRepository.save(paciente)
           }

       } catch(ex: Exception){
           throw ResponseStatusException(
               HttpStatus.NOT_FOUND, ex.message, ex)
       }
    }


    fun updateApellido (paciente: Paciente):Paciente {
        try {

            paciente.apellido?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo apellido esta vacio")


            val response = pacienteRepository.findById(paciente.idpaciente)
                ?: throw Exception("El ID ${paciente.idpaciente} de paciente no existe")


            response.apply {
                this.apellido=paciente.apellido
            }
            return pacienteRepository.save(response)

        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (idPaciente:Long?): Boolean{

            try {
                pacienteRepository.findById(idPaciente)
                    ?: throw Exception("El ID de paciente no existe")
                pacienteRepository.deleteById(idPaciente!!)
                return true

            }catch(ex: Exception){
                throw Exception()
            }
         }

}