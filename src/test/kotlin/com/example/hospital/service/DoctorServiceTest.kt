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


    //SAVE

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

    //SAVE

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
    fun saveDoctor(){
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
    fun saveDoctorFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { nombre= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

    @Test
    fun saveDoctorFailedApellido(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { apellido= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

    @Test
    fun saveDoctorFailedCedula(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { cedula= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

    @Test
    fun saveDoctorFailedEspecialidad(){
        Assertions.assertThrows(Exception::class.java) {
            doctorMock.apply { especialidad= " "}

            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(doctorMock)
            doctorService.save(doctorMock)
        }

    }

    //UPDATE


    @Test
    fun updateDoctorIsIdCorrect(){
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
    fun updateDoctorIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(doctorRepository.findById(returnObject.iddoctor)).thenReturn(null)
            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(returnObject)
            doctorService.update(newObject)
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

    //DELETE

    @Test
    fun deleteDoctorCorrect(){
        Mockito.`when`(doctorRepository.findById(newObject.iddoctor)).thenReturn(returnObject)
        Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(returnObject)
        val response = doctorService.delete(newObject.iddoctor)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteDoctorIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(doctorRepository.findById(newObject.iddoctor)).thenReturn(null)
            Mockito.`when`(doctorRepository.save(Mockito.any(Doctor::class.java))).thenReturn(returnObject)
            val response = doctorService.delete(newObject.iddoctor)
            Assertions.assertEquals(response, true)
        }
    }

}