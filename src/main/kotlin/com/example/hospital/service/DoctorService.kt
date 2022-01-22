package com.example.hospital.service

import com.example.hospital.model.Doctor
import com.example.hospital.model.Medicina
import com.example.hospital.repository.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class DoctorService {
    @Autowired
    lateinit var doctorRepository: DoctorRepository


    fun list(): List<Doctor> {

        return doctorRepository.findAll()
    }


    fun save (@RequestBody doctor: Doctor): Doctor {
 try {

        doctor.nombre?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo nombre esta vacio")

        doctor.apellido?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo apellido esta vacio")

        doctor.cedula?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo cedula esta vacio")

        doctor.especialidad?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo especialidad esta vacio")

     if (doctor.cedula!!.length == 11 ) {
         throw Exception("La cedula debe tener 10 digitos")

     } else {
         return doctorRepository.save(doctor) }

  }  catch(ex: Exception){
     throw ResponseStatusException(
         HttpStatus.NOT_FOUND, ex.message, ex)
  }
    }


    fun update (@RequestBody doctor: Doctor): Doctor

    {
 try {

     val response = doctorRepository.findById(doctor.iddoctor)
         ?: throw Exception("El ID ${doctor.iddoctor} de doctor no existe")

     doctor.nombre?.takeIf { it.trim().isNotEmpty() }
         ?: throw Exception("El campo nombre esta vacio")

     doctor.apellido?.takeIf { it.trim().isNotEmpty() }
         ?: throw Exception("El campo apellido esta vacio")

     doctor.cedula?.takeIf { it.trim().isNotEmpty() }
         ?: throw Exception("El campo cedula esta vacio")

     doctor.especialidad?.takeIf { it.trim().isNotEmpty() }
         ?: throw Exception("El campo especialidad esta vacio")


     if (doctor.cedula!!.length == 11 ) {
        throw Exception("La cedula debe tener 10 digitos")
    } else {
        return doctorRepository.save(doctor)
    }
} catch(ex: Exception){
     throw ResponseStatusException(
         HttpStatus.NOT_FOUND, ex.message, ex)

    }
    }


    fun updateApellido (doctor: Doctor): Doctor {
      try {
          doctor.apellido?.takeIf {it.trim().isNotEmpty()}
              ?: throw Exception("El campo apellido esta vacio")


          val response = doctorRepository.findById(doctor.iddoctor)
              ?: throw Exception("El ID ${doctor.iddoctor} de doctor no existe")
          response.apply {
              this.apellido=doctor.apellido
          }
          return doctorRepository.save(response)

      }catch(ex: Exception){
          throw ResponseStatusException(
              HttpStatus.NOT_FOUND, ex.message, ex)
      }
    }


    fun delete (idDoctor:Long): Boolean{
        doctorRepository.deleteById(idDoctor)
        return true
    }

    fun verificarLetras(cedula: String?, nombre: String?, apellido: String?):Boolean{
        if (cedula?.length!! == 10){
            return false
        }
        if (nombre?.length!! < 20){
            return false
        }
        if (apellido?.length!! < 20){
            return false
        }

        return true
    }
}