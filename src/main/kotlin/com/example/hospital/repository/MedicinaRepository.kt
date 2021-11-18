package com.example.hospital.repository

import com.example.hospital.model.Medicina
import org.springframework.data.jpa.repository.JpaRepository

interface MedicinaRepository: JpaRepository<Medicina, Long> {
    fun findById (id: Long?): Medicina
}