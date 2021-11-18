package com.example.hospital.service

import com.example.hospital.model.Paciente
import com.example.hospital.repository.PacienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody


@Service
class PacienteService {

    @Autowired
    lateinit var pacienteRepository: PacienteRepository


    fun list(): List<Paciente> {

        return pacienteRepository.findAll()

    }

    fun save (@RequestBody paciente: Paciente): Paciente {
        return pacienteRepository.save(paciente)

    }

    fun update (@RequestBody paciente: Paciente): Paciente {
        return pacienteRepository.save(paciente)
    }

    fun updateApellido (paciente: Paciente):Paciente {
        val response = pacienteRepository.findById(paciente.idpaciente)
            ?: throw Exception()
        response.apply {
            this.apellido=paciente.apellido
        }
        return pacienteRepository.save(response)
    }

    fun delete (idPaciente:Long): Boolean{
        pacienteRepository.deleteById(idPaciente)
        return true
    }

}