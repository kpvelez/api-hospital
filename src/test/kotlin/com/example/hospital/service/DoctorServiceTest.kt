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
class DoctorServiceTest {

    @InjectMocks
    lateinit var doctorService: DoctorService

    @Mock
    lateinit var doctorRepository: DoctorRepository



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

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(returnObject)
        val response = doctorService.save(newObject)
        Assertions.assertEquals(response.iddoctor, newObject.iddoctor)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.apellido, newObject.apellido)
        Assertions.assertEquals(response.cedula, newObject.cedula)
        Assertions.assertEquals(response.especialidad, newObject.especialidad)

    }

    val jsonString = File("./src/test/resources/doctor/crearDoctor.json").readText(Charsets.UTF_8)
    val doctorMock = Gson().fromJson(jsonString, Doctor::class.java)

    @Test
    fun saveDueno(){
        //PAra actualizar
        //Mockito.`when`(dietRepository.findById(dietMock.id)).thenReturn(dietMock)
        Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
        val response = doctorService.save(doctorMock)
        Assertions.assertEquals(response.iddoctor, doctorMock.iddoctor)
        Assertions.assertEquals(response.nombre, doctorMock.nombre)
        Assertions.assertEquals(response.apellido, doctorMock.apellido)
        Assertions.assertEquals(response.cedula, doctorMock.cedula)
        Assertions.assertEquals(response.especialidad, doctorMock.especialidad)
    }

    @Test
    fun saveDuenoFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { nombre= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

    @Test
    fun saveDuenoFailedApellido(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { apellido= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

    @Test
    fun saveDuenoFailedCedula(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { cedula= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

    @Test
    fun saveDuenoFailedEspecialidad(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { especialidad= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

}