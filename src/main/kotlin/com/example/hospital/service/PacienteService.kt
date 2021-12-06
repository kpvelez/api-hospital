package com.example.hospital.service

import com.example.hospital.model.Paciente
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


    fun list(): List<Paciente> {

        return pacienteRepository.findAll()

    }

    fun save (@RequestBody paciente: Paciente): Paciente {

            if(paciente.nombre.equals("") || paciente.apellido.equals("") || paciente.cedula.equals("") ||
                paciente.enfermedad.equals("") ) {
                throw Exception("Uno de los campos esta vacio")
            }

            if (paciente.cedula!!.length == 10 ) {
                throw Exception("La cedula debe tener 10 digitos")

            } else {
                return pacienteRepository.save(paciente)
            }


    }

    fun update (@RequestBody paciente: Paciente): Paciente {

       try {

           val response = pacienteRepository.findById(paciente.idpaciente)
               ?: throw Exception("El ID ${paciente.idpaciente}  no existe")

           if (paciente.nombre.equals("") || paciente.apellido.equals("") || paciente.cedula.equals("") ||
               paciente.enfermedad.equals("")) {
               throw Exception("Uno de los campos esta vacio")
           }

           if (paciente.cedula!!.length == 10 ) {
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
            if (paciente.apellido.equals("")){
                throw Exception("El campo apellido esta vacio")
            }

            val response = pacienteRepository.findById(paciente.idpaciente)
                ?: throw Exception("El ID ${paciente.idpaciente}  no existe")

            response.apply {
                this.apellido=paciente.apellido
            }
            return pacienteRepository.save(response)

        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (idPaciente:Long): Boolean{
        pacienteRepository.deleteById(idPaciente)
        return true
    }

}