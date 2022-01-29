package com.example.hospital.service



import com.example.hospital.model.User
import com.example.hospital.repository.UserRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @InjectMocks
    lateinit var userServic: UserService

    @Mock
    lateinit var userRepository: UserRepository

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



    val returnObject: User = User().apply {
        id= 1
        username="Paul"
        password= "2001"
        cedula= "0106362338"
    }
    val newObject: User = User().apply {
        id= 1
        username="Paul"
        password= "2001"
        cedula= "0106362338"
    }

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(returnObject)
        val response = userServic.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.username, newObject.username)
        Assertions.assertEquals(response.password, newObject.password)
        Assertions.assertEquals(response.cedula, newObject.cedula)

    }

    val jsonString = File("./src/test/resources/user/crearUser.json").readText(Charsets.UTF_8)
    val userMock = Gson().fromJson(jsonString, User::class.java)

    @Test
    fun saveUser(){
        //PAra actualizar
        //Mockito.`when`(dietRepository.findById(dietMock.id)).thenReturn(dietMock)
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(userMock)
        val response = userServic.save(userMock)
        Assertions.assertEquals(response.id, userMock.id)
        Assertions.assertEquals(response.username, userMock.username)
        Assertions.assertEquals(response.password, userMock.password)
        Assertions.assertEquals(response.cedula, userMock.cedula)

    }

    @Test
    fun saveUserFailedUserName(){
        Assertions.assertThrows(Exception::class.java) {
            userMock.apply { username= " "}

            Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(userMock)
            userServic.save(userMock)
        }

    }

    @Test
    fun saveUserFailedPassword(){
        Assertions.assertThrows(Exception::class.java) {
            userMock.apply { password= " "}

            Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(userMock)
            userServic.save(userMock)
        }

    }

    @Test
    fun saveUserFailedCedula(){
        Assertions.assertThrows(Exception::class.java) {
            userMock.apply { cedula= " "}

            Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(userMock)
            userServic.save(userMock)
        }

    }



}