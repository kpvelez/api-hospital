package com.example.hospital.service


import com.example.hospital.model.Doctor
import com.example.hospital.model.Medicina
import com.example.hospital.repository.DoctorRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class DoctorServiceTestEva {

    @InjectMocks
    lateinit var doctorService: DoctorService

    @Mock
    lateinit var doctorRepository: DoctorRepository


    //UPDATE


    val returnObject: Doctor = Doctor().apply {
        iddoctor= 1
        nombre="Paul"
        apellido= "Velez"
        cedula= "0106362338"
        especialidad="Cirujano"
    }
    val newObject: Doctor = Doctor().apply {
        iddoctor= 1
        nombre="Paul"
        apellido= "Velez"
        cedula= "0106362338"
        especialidad="Cirujano"
    }



    val jsonString = File("./src/test/resources/doctor/updateDoctor.json").readText(Charsets.UTF_8)
    val doctorMock = Gson().fromJson(jsonString, Doctor::class.java)



    @Test
    fun updateDoctorIsCorrect(){
        Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(returnObject)

        Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(returnObject)
        val response = doctorService.update(newObject)
        Assertions.assertEquals(response.iddoctor, newObject.iddoctor)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.apellido, newObject.apellido)
        Assertions.assertEquals(response.cedula, newObject.cedula)
        Assertions.assertEquals(response.especialidad, newObject.especialidad)
    }


    @Test
    fun updateDoctorIsFailed() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(null)

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(returnObject)
            doctorService.update(newObject)
        }
    }


    @Test
    fun updateDoctorIsPassedList(){

        Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(returnObject)
        Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(returnObject)
        val response = doctorService.update(newObject)
        Assertions.assertEquals(response.iddoctor, newObject.iddoctor)
        Assertions.assertEquals(response.nombre, newObject.nombre)

    }


    @Test
    fun updateDoctorIsPassedListFaild(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { nombre= " "}
            Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(doctorMock)
            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.update(doctorMock)
        }
    }



    @Test
    fun updateDoctorFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { nombre= " "}
            Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(doctorMock)
            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.update(doctorMock)
        }
    }


    @Test
    fun updateDoctorFailedApellido(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { apellido= " "}
            Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(doctorMock)
            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.update(doctorMock)
        }
    }


    @Test
    fun updateDoctorFailedCedula(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { cedula= " "}
            Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(doctorMock)
            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.update(doctorMock)
        }
    }


    @Test
    fun updateDoctorFailedEspecialidad(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { especialidad= " "}
            Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(doctorMock)
            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.update(doctorMock)
        }
    }


}