package com.example.hospital.service


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun calcMultiplicationIfIsPair(){
     val response = userService.calcMultiplication(2,2)
        Assertions.assertEquals(4,response)

    }

    @Test
    fun calcMultiplicationIfIsNotPair(){
        val response = userService.calcMultiplication(2,1)
        Assertions.assertEquals(2,response)

    }

    @Test
    fun restNineIfIsLessTen(){
        val response = userService.restNine(8)
        Assertions.assertEquals(8, response)

    }

    @Test
    fun restNineIfIsMajorTen(){
        val response = userService.restNine(10)
        Assertions.assertEquals(1, response)

    }

    @Test
    fun subtactFromNextTen(){
        val response = userService.subtactFromNextTen(18)
        Assertions.assertEquals(2, response)
    }

    @Test
    fun validarCedulaEc(){
        val response = userService.validarCedula("0106362338")
        Assertions.assertEquals(true, response)
    }


}