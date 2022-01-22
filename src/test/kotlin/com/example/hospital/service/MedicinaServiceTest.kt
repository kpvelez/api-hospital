package com.example.hospital.service


import com.example.hospital.model.Medicina
import com.example.hospital.model.Paciente
import com.example.hospital.repository.MedicinaRepository
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
class MedicinaServiceTest {

    @InjectMocks
    lateinit var medicinaService: MedicinaService

    @Mock
    lateinit var medicinaRepository: MedicinaRepository

    @Mock
    lateinit var pacienteRepository: PacienteRepository



    val returnObject: Medicina = Medicina().apply {
        idmedicina= 1
        medicamento= "paracetamol"
        via= "oral"
        dosis= "5"
        fecha= "09/11/2004"
        pacienteIdPaciente= 1

    }
    val newObject: Medicina = Medicina().apply {
        idmedicina= 1
        medicamento= "paracetamol"
        via= "oral"
        dosis= "5"
        fecha= "09/11/2004"
        pacienteIdPaciente= 1
    }

    @Test
    fun saveIsCorrect(){

        Mockito.`when`(pacienteRepository.findById(medicinaMock.pacienteIdPaciente)).thenReturn(pacienteMock)

        Mockito.`when`(medicinaRepository.save(Mockito.any(Medicina::class.java))).thenReturn(returnObject)
        val response = medicinaService.save(newObject)
        Assertions.assertEquals(response.idmedicina, newObject.idmedicina)
        Assertions.assertEquals(response.medicamento, newObject.medicamento)
        Assertions.assertEquals(response.medicamento, newObject.medicamento)
        Assertions.assertEquals(response.dosis, newObject.dosis)
        Assertions.assertEquals(response.fecha, newObject.fecha)
        Assertions.assertEquals(response.pacienteIdPaciente, newObject.pacienteIdPaciente)

    }

    val jsonString = File("./src/test/resources/medicina/crearMedicina.json").readText(Charsets.UTF_8)
    val medicinaMock = Gson().fromJson(jsonString, Medicina::class.java)

    val jsonString1 = File("./src/test/resources/paciente/crearPaciente.json").readText(Charsets.UTF_8)
    val pacienteMock = Gson().fromJson(jsonString1, Paciente::class.java)

    @Test
    fun saveMedicina(){
        //PAra actualizar
        Mockito.`when`(pacienteRepository.findById(medicinaMock.pacienteIdPaciente)).thenReturn(pacienteMock)

        Mockito.`when`(medicinaRepository.save(Mockito.any(Medicina::class.java))).thenReturn(medicinaMock)
        val response = medicinaService.save(medicinaMock)
        Assertions.assertEquals(response.idmedicina, medicinaMock.idmedicina)
        Assertions.assertEquals(response.via, medicinaMock.via)
        Assertions.assertEquals(response.medicamento, medicinaMock.medicamento)
        Assertions.assertEquals(response.dosis, medicinaMock.dosis)
        Assertions.assertEquals(response.fecha, medicinaMock.fecha)
        Assertions.assertEquals(response.pacienteIdPaciente, medicinaMock.pacienteIdPaciente)
    }

    @Test
    fun saveMedicinaFailedMedicamento(){
        Assertions.assertThrows(Exception::class.java) {
            medicinaMock.apply { medicamento= " "}

            Mockito.`when`(medicinaRepository.save(Mockito.any(Medicina::class.java))).thenReturn(medicinaMock)
            medicinaService.save(medicinaMock)
        }

    }

    @Test
    fun saveMedicinaFailedVia(){
        Assertions.assertThrows(Exception::class.java) {
            medicinaMock.apply { via= " "}

            Mockito.`when`(medicinaRepository.save(Mockito.any(Medicina::class.java))).thenReturn(medicinaMock)
            medicinaService.save(medicinaMock)
        }

    }

    @Test
    fun saveMedicinaFailedDosis(){
        Assertions.assertThrows(Exception::class.java) {
            medicinaMock.apply { dosis= " "}

            Mockito.`when`(medicinaRepository.save(Mockito.any(Medicina::class.java))).thenReturn(medicinaMock)
            medicinaService.save(medicinaMock)
        }

    }

    @Test
    fun saveMedicinaFailedFecha(){
        Assertions.assertThrows(Exception::class.java) {
            medicinaMock.apply { fecha= " "}

            Mockito.`when`(medicinaRepository.save(Mockito.any(Medicina::class.java))).thenReturn(medicinaMock)
            medicinaService.save(medicinaMock)
        }

    }

    @Test
    fun saveMedicinaFailedIdPaciente(){
        Assertions.assertThrows(Exception::class.java) {
            medicinaMock.apply { pacienteIdPaciente }

            Mockito.`when`(medicinaRepository.save(Mockito.any(Medicina::class.java))).thenReturn(medicinaMock)
            medicinaService.save(medicinaMock)
        }


    }

}