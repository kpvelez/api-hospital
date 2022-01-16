package com.example.hospital

import com.example.hospital.service.DoctorService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HospitalApplicationTests {

	@Autowired
	lateinit var doctorService: DoctorService;

	@Test
	fun contextLoads() {
	}

	@Test
	fun verifySizeWordWhenIsIncorrect(){
		val response: Boolean = doctorService.verificarLetras("01023", "Pablo Pedro Alvarracin Gomez", "Pablo Pedro Alvarracin Gomez")
		Assertions.assertEquals(false,response)
	}

	@Test
	fun verifySizeWordWhenIsCorrect(){
		val response: Boolean = doctorService.verificarLetras("0106362338", "Pablo Pedro", "Alvarracin Gomez")
		Assertions.assertEquals(true,response)
	}


}
