package com.example.hospital.service

import com.example.hospital.model.Doctor
import com.example.hospital.model.Paciente
import com.example.hospital.repository.DoctorRepository
import com.example.hospital.repository.PacienteRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class PacienteServiceTest {

    @InjectMocks
    lateinit var pacienteService: PacienteService

    @Mock
    lateinit var pacienteRepository: PacienteRepository

    @Mock
    lateinit var doctorRepository: DoctorRepository



    val returnObject: Paciente = Paciente().apply {
            idpaciente= 1
            nombre= "Pedro"
            apellido= "Ulloa"
            cedula= "010636221"
            enfermedad= "Pulmonia"
            doctorIdDoctor= 1

    }
    val newObject: Paciente = Paciente().apply {
        idpaciente= 1
        nombre= "Pedro"
        apellido= "Ulloa"
        cedula= "010636221"
        enfermedad= "Pulmonia"
        doctorIdDoctor= 1
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(doctorRepository.findById(pacienteMock.doctorIdDoctor)).thenReturn(doctorMock)

        Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(returnObject)
        val response = pacienteService.save(newObject)
        Assertions.assertEquals(response.idpaciente, newObject.idpaciente)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.apellido, newObject.apellido)
        Assertions.assertEquals(response.cedula, newObject.cedula)
        Assertions.assertEquals(response.enfermedad, newObject.enfermedad)
        Assertions.assertEquals(response.doctorIdDoctor, newObject.doctorIdDoctor)

    }

    val jsonString = File("./src/test/resources/paciente/crearPaciente.json").readText(Charsets.UTF_8)
    val pacienteMock = Gson().fromJson(jsonString, Paciente::class.java)

    val jsonString1 = File("./src/test/resources/doctor/crearDoctor.json").readText(Charsets.UTF_8)
    val doctorMock = Gson().fromJson(jsonString1, Doctor::class.java)


    @Test
    fun savePaciente(){
        //PAra actualizar
        Mockito.`when`(doctorRepository.findById(pacienteMock.doctorIdDoctor)).thenReturn(doctorMock)

        Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
        val response = pacienteService.save(pacienteMock)
        Assertions.assertEquals(response.idpaciente, pacienteMock.idpaciente)
        Assertions.assertEquals(response.nombre, pacienteMock.nombre)
        Assertions.assertEquals(response.apellido, pacienteMock.apellido)
        Assertions.assertEquals(response.cedula, pacienteMock.cedula)
        Assertions.assertEquals(response.enfermedad, pacienteMock.enfermedad)
        Assertions.assertEquals(response.doctorIdDoctor, pacienteMock.doctorIdDoctor)
    }

    @Test
    fun savePacienteFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { nombre= " "}

            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.save(pacienteMock)
        }

    }

    @Test
    fun savePacienteFailedApellido(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { apellido= " "}

            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.save(pacienteMock)
        }

    }

    @Test
    fun savePacienteFailedCedula(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { cedula= " "}

            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.save(pacienteMock)
        }

    }

    @Test
    fun savePacienteFailedEnfermedad(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { enfermedad= " "}

            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.save(pacienteMock)
        }

    }

    @Test
    fun savePacienteFailedIdDoctor(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { doctorIdDoctor }

            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.save(pacienteMock)
        }

    }

    //UPDATE


    @Test
    fun updateDoctorIsIdCorrect(){

        Mockito.`when`(doctorRepository.findById(pacienteMock.doctorIdDoctor)).thenReturn(doctorMock)
        Mockito.`when`(pacienteRepository.findById(returnObject.idpaciente)).thenReturn(returnObject)
        Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(returnObject)
        val response = pacienteService.update(newObject)
        Assertions.assertEquals(response.idpaciente, newObject.idpaciente)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.apellido, newObject.apellido)
        Assertions.assertEquals(response.cedula, newObject.cedula)
        Assertions.assertEquals(response.enfermedad, newObject.enfermedad)
        Assertions.assertEquals(response.doctorIdDoctor, newObject.doctorIdDoctor)
    }

    @Test
    fun updatePacienteIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){

            Mockito.`when`(doctorRepository.findById(pacienteMock.doctorIdDoctor)).thenReturn(doctorMock)
            Mockito.`when`(pacienteRepository.findById(returnObject.idpaciente)).thenReturn(null)
            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(returnObject)
            pacienteService.update(newObject)
        }
    }

    @Test
    fun updatePacienteFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { nombre= " "}
            Mockito.`when`(pacienteRepository.findById(returnObject.idpaciente)).thenReturn(pacienteMock)
            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.update(pacienteMock)
        }
    }

    @Test
    fun updatePacienteFailedApellido(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { apellido= " "}
            Mockito.`when`(pacienteRepository.findById(returnObject.idpaciente)).thenReturn(pacienteMock)
            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.update(pacienteMock)
        }

    }

    @Test
    fun updatePacienteFailedCedula(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { cedula= " "}
            Mockito.`when`(pacienteRepository.findById(returnObject.idpaciente)).thenReturn(pacienteMock)
            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.update(pacienteMock)
        }

    }

    @Test
    fun updatePacienteFailedEnfermedad(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { enfermedad= " "}
            Mockito.`when`(pacienteRepository.findById(returnObject.idpaciente)).thenReturn(pacienteMock)
            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.update(pacienteMock)
        }
    }

    @Test
    fun updatePacienteFailedIdDoctor(){
        Assertions.assertThrows(Exception::class.java) {
            pacienteMock.apply { doctorIdDoctor}
            Mockito.`when`(pacienteRepository.findById(returnObject.idpaciente)).thenReturn(pacienteMock)
            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(pacienteMock)
            pacienteService.update(pacienteMock)
        }
    }


    //DELETE

    @Test
    fun deleteDoctorCorrect(){
        Mockito.`when`(pacienteRepository.findById(newObject.idpaciente)).thenReturn(returnObject)
        Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(returnObject)
        val response = pacienteService.delete(newObject.idpaciente)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteDoctorIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(pacienteRepository.findById(newObject.idpaciente)).thenReturn(null)
            Mockito.`when`(pacienteRepository.save(Mockito.any(Paciente::class.java))).thenReturn(returnObject)
            val response = pacienteService.delete(newObject.idpaciente)
            Assertions.assertEquals(response, true)
        }
    }
}