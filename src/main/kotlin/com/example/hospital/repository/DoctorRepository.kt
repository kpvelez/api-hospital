package com.example.hospital.repository

import com.example.hospital.model.Doctor
import com.example.hospital.model.Medicina
import org.springframework.data.jpa.repository.JpaRepository

interface DoctorRepository: JpaRepository<Doctor, Long> {
    fun findById (id: Long?): Doctor?
}