package com.example.hospital.repository

import com.example.hospital.model.Paciente
import org.springframework.data.jpa.repository.JpaRepository

interface PacienteRepository: JpaRepository<Paciente, Long> {
    fun findById (id: Long?): Paciente?
}