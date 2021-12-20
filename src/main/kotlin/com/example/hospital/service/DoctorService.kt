package com.example.hospital.service

import com.example.hospital.model.Doctor
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
    if(doctor.nombre.equals("") || doctor.apellido.equals("") || doctor.cedula.equals("")
        || doctor.especialidad.equals("") ) {

        throw Exception( "Uno de los campos esta vacio")
    } else {
        return doctorRepository.save(doctor)
    }
  }  catch(ex: Exception){
     throw ResponseStatusException(
         HttpStatus.NOT_FOUND, ex.message, ex)
  }
    }


    fun update (@RequestBody doctor: Doctor): Doctor {
 try {

     val response = doctorRepository.findById(doctor.iddoctor)
         ?: throw Exception("El ID ${doctor.iddoctor}  no existe")

    if (doctor.nombre.equals("") || doctor.apellido.equals("") || doctor.cedula.equals("")
        || doctor.especialidad.equals("")){
        throw Exception( "Uno de los campos esta vacio")
    }

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
              ?: throw Exception("El ID ${doctor.iddoctor}  no existe")
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

}