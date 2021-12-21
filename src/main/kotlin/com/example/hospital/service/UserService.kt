package com.example.hospital.service

import com.example.hospital.model.Doctor
import com.example.hospital.model.User
import com.example.hospital.repository.DoctorRepository
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
            if(user.username.equals("") || user.password.equals("")) {

                throw Exception( "Uno de los campos esta vacio")
            } else {
                return userRepository.save(user)
            }
        }  catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }


    fun update (@RequestBody user: User): User {
        try {

            val response = userRepository.findById(user.id)
                ?: throw Exception("El ID ${user.id} de usuarios no existe")

            if (user.username.equals("") || user.password.equals("")){
                throw Exception( "Uno de los campos esta vacio")
            } else {
                return userRepository.save(user)
            }
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


}