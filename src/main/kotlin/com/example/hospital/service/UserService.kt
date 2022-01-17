package com.example.hospital.service

import com.example.hospital.model.User
import com.example.hospital.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository


    fun list(): List<User> {
        return userRepository.findAll()
    }

    fun getUser (username:String?):User? {
        try {
             val response = userRepository.findByUsername(username)
               ?: throw Exception("No existe usuario")
            return response
            } catch(ex: Exception){
                  throw ResponseStatusException(
                   HttpStatus.NOT_FOUND, ex.message, ex)
            }
    }

    fun save (@RequestBody user: User): User {

        try {

                user.username?.takeIf {it.trim().isNotEmpty()}
                    ?: throw Exception("El campo username esta vacio")

                user.password?.takeIf {it.trim().isNotEmpty()}
                    ?: throw Exception("El campo password esta vacio")

                user.cedula?.takeIf {it.trim().isNotEmpty()}
                    ?: throw Exception("El campo cedula esta vacio")

                return userRepository.save(user)

         }  catch(ex: Exception){
             throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }


    fun update (@RequestBody user: User): User {
        try {

            val response = userRepository.findById(user.id)
                ?: throw Exception("El ID ${user.id} de usuarios no existe")

            user.username?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo username esta vacio")

            user.password?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo password esta vacio")

            user.cedula?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo cedula esta vacio")

            return userRepository.save(user)

        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)

        }
    }

    fun updatePassword (user: User): User {
        try {
            user.password?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo password esta vacio")


            val response = userRepository.findById(user.id)
                ?: throw Exception("El ID ${user.id} de usuarios no existe")
            response.apply {
                this.password=user.password
            }
            return userRepository.save(response)

        }catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (id:Long): Boolean{
        userRepository.deleteById(id)
        return true
    }

    fun calcMultiplication(index: Int, number: Int):Int {
        if(index %2 == 0){
            return number * 2

        } else {
            return number
        }
    }

    fun restNine(number: Int):Int {
        if(number in 10..18){
            return number -  9
        }
        return number
    }

    fun subtactFromNextTen(number: Int):Int {
            val decimal =  (number/10) + 1
            val respuesta = (decimal*10) - number
        return respuesta
    }

    fun validarCedula(cedula: String): Boolean {

        val primerosDig = cedula.substring(0,2)
        val d1 = cedula.substring(0,1)
        val d2 = cedula.substring(1,2)
        val d3 = cedula.substring(2,3)
        val d4 = cedula.substring(3,4)
        val d5 = cedula.substring(4,5)
        val d6 = cedula.substring(5,6)
        val d7 = cedula.substring(6,7)
        val d8 = cedula.substring(7,8)
        val d9 = cedula.substring(8,9)
        val d10 = cedula.substring(9,10)

        if (cedula.length != 10) {
            return false
        }

        if (primerosDig.toInt() < 1 || primerosDig.toInt() > 24){
            return false
        }
        if (d3.toInt() > 6){
            return false
        }
        val pares = d2.toInt() + d4.toInt() + d6.toInt() + d8.toInt()

        val Impar = d1.toInt() * 2
        if (Impar > 9){
            Impar - 9
        }
        var Impar1 = d3.toInt() * 2
        if (Impar1 > 9){
            Impar1 -= 9
        }
        var Impar2 = d5.toInt() * 2
        if (Impar2 > 9){
            Impar2 -= 9
        }
        var Impar3 = d7.toInt() * 2
        if (Impar3 > 9){
            Impar3 -= 9
        }
        var Impar4 = d9.toInt() * 2
        if (Impar4 > 9){
            Impar4 -= 9
        }
        val impares = Impar + Impar1 + Impar2 + Impar3 + Impar4
        val sumaParImpar = pares + impares

        val decena = (sumaParImpar/10) + 1
        var validacion = (decena*10) - sumaParImpar
        if (validacion == 10) {
            validacion = 0
        }
          if(validacion != d10.toInt()){
              return false
          }

        return true
    }
}


